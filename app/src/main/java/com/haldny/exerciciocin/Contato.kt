package com.haldny.exerciciocin

data class Contato(val ID: String?, val name: String?, val telefones: List<Telefone>?)

data class Telefone(val ID: String?, val telefone: String?)