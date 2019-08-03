package org.generation.brazil.ecommerce.dadospessoais;

import com.github.javafaker.Faker;
import org.generation.brazil.ecommerce.cliente.Cliente;
import org.generation.brazil.ecommerce.cliente.ClienteMock;

public class DadosPessoaisMock {
    public static DadosPessoais getDadosPessoaisMock(){
        Faker faker = new Faker();
        DadosPessoais dadosPessoais = new DadosPessoais();
        dadosPessoais.setCpf(faker.numerify("###.###.###-##"));
        dadosPessoais.setTelefone(faker.numerify("(##)#####-####"));
        return dadosPessoais;
    }
}
