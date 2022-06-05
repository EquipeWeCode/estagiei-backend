package br.edu.ifsp.estagiei.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/api")
public interface IController {
}
