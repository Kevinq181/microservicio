package com.example.demo.model;

import java.util.List;
import java.util.Optional;

public interface IUsuarioService {

    public List<Usuario> listar();

    public Optional<Usuario> listarId(int id);

    public int save(Usuario p);

    public void delete(int id);
}
