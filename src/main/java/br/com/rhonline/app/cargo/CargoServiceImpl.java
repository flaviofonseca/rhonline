package br.com.rhonline.app.cargo;

import br.com.rhonline.core.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CargoServiceImpl implements CargoService {

    private final CargoRepository repository;

    @Autowired
    public CargoServiceImpl(CargoRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Cargo salvar(Cargo cargo) {
        Cargo cargoJaSalvo = repository.findByNomeCargo(cargo.getNomeCargo(), Optional.ofNullable(cargo.getId()).orElse(0l));
        if (cargoJaSalvo != null) {
            throw new BusinessException("Já existe um registro com essa descrição");
        }
        return repository.save(cargo);
    }

    @Override
    @Transactional
    public void excluir(long id) {
        repository.deleteById(id);
    }

    @Override
    public Cargo obterPeloId(long id) {
        return repository.findById(id).orElse(new Cargo());
    }

    @Override
    public List<Cargo> listarTodos() {
        return repository.findAll();
    }

    @Override
    public List<Cargo> obterPeloTermo(String termo) {
        return repository.findAll()
                .stream()
                .filter(c -> c.getNomeCargo().toLowerCase().contains(termo.toLowerCase()))
                .collect(Collectors.toList());
    }
}
