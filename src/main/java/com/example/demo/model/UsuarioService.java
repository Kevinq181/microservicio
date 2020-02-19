package com.example.demo.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService implements IUsuarioService {
    @Autowired
    private IUsuario dao;

    @Override
    public List<Usuario> listar() {
        return (List<Usuario>) dao.findAll();
    }

    @Override
    public Optional<Usuario> listarId(int id) {
        return dao.findById(id);
    }

    @Override
    public int save(Usuario p) {
        int res=0;
        Usuario per=dao.save(p);
        if(!per.equals(null)) {
            res=1;
        }
        return res;
    }

    @Override
    public void delete(int id) {
        dao.deleteById(id);

    }

}
