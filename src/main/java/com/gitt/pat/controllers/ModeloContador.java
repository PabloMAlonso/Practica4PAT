package com.gitt.pat.controllers;

import java.util.List;

public record ModeloContador(Integer id, String name, String image, Double rating, String summary, List<String> genres) {
}
