package org.generation.brazil.ecommerce.cliente;

import com.github.javafaker.Faker;
import org.generation.brazil.ecommerce.dadospessoais.DadosPessoaisMock;
import org.generation.brazil.ecommerce.endereco.Endereco;
import org.generation.brazil.ecommerce.endereco.EnderecoMock;
import org.generation.brazil.ecommerce.pedido.Pedido;
import org.generation.brazil.ecommerce.pedido.PedidoMock;
import org.generation.brazil.ecommerce.user.UserMock;

import javax.validation.constraints.Size;
import java.util.HashSet;

public class ClienteMock {
    public static Cliente getClienteMock(){
        Cliente cliente = new Cliente();

        cliente.setDadosPessoais(DadosPessoaisMock.getDadosPessoaisMock());

        HashSet<Endereco> enderecos = new HashSet<>();
        enderecos.add(EnderecoMock.getEnderecoMock());
        cliente.setEnderecos(enderecos);

        HashSet<Pedido> pedidos = new HashSet<>();
        pedidos.add(PedidoMock.getPedidoMock());
        cliente.setPedidos(pedidos);

        cliente.setUser(UserMock.getUserMock());
        return cliente;
    }
}
