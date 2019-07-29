package br.com.ecommerce.ecommerce.cliente;

import com.github.javafaker.Faker;

public class ClienteMock {
    public static Cliente getClienteMock(){
        Faker faker = new Faker();
        Cliente cliente = new Cliente();
        cliente.setIdDadosPessoais(Long.valueOf(faker.number().digits(1)));
        return cliente;
    }
}
