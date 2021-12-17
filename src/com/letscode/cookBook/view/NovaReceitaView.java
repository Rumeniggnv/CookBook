package com.letscode.cookBook.view;

import com.letscode.cookBook.domain.Ingrediente;
import com.letscode.cookBook.domain.Receita;
import com.letscode.cookBook.domain.Rendimento;
import com.letscode.cookBook.enums.Categoria;
import com.letscode.cookBook.enums.TipoMedida;
import com.letscode.cookBook.enums.TipoRendimento;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NovaReceitaView {
    Scanner scanner;
    Receita receita;
    String nome;

    public NovaReceitaView() {
        this.scanner = new Scanner(System.in);
    }

    public String askNome() {
        System.out.print("Qual o nome da receita?: ");
        nome = scanner.nextLine();
        if (nome.isBlank()) {
            System.out.println("Nome inválido!");
            askNome();
        }
        return nome;
    }

    public Categoria askCategoria() {
        for (Categoria cat : Categoria.values()) {
            System.out.printf("%d - %s\n", cat.ordinal(), cat.name());
        }
        System.out.print("Qual a categoria da receita?: ");
        int categoria = scanner.nextInt();
        if (categoria < 0 || categoria >= Categoria.values().length) {
            System.out.println("Categoria inválida!");
            askCategoria();
        }
        //System.out.println(Categoria.values()[categoria]);
        return Categoria.values()[categoria];
    }

    public int askTempoPreparo() {
        System.out.print("Qual o tempo de preparo da receita?: ");
        int tempo = scanner.nextInt();
        if ( tempo <= 0 ) {
            System.out.println("Tempo inválido! Informe um tempo maior do que zero, tente novamente");
            askTempoPreparo();
        }
        return tempo;
    }

    public Rendimento askRendimento() {
        boolean quantidadeOk = false;
        int quantidade = 0;

        if ( !quantidadeOk ) {
            System.out.print("Qual é o rendimento da receita?: ");
            quantidade = scanner.nextInt();
            if (quantidade <= 0) {
                System.out.println("Rendimento inválido! O rendimento não pode ser menor ou igual a zero, tente novamente");
                askRendimento();
            }
            quantidadeOk = true;
        }
        for ( TipoRendimento tipo : TipoRendimento.values() ) {
            System.out.printf("%d - %s\n", tipo.ordinal(), tipo.name() );
        }
        System.out.print("Qual o tipo de rendimento da receita?: ");
        int tipoRendimento = scanner.nextInt();
        if ( tipoRendimento < 0 || tipoRendimento >= Categoria.values().length ) {
            System.out.println("Tipo de rendimento inválido!");
            askRendimento();
        }

        return new Rendimento(quantidade, TipoRendimento.values()[tipoRendimento]);
    }

    public List<Ingrediente> askIngredientes(){
        List<Ingrediente> ingredientes = new ArrayList<>();
        String op = "s";
        do {
            System.out.print("Informe o nome do ingrediente: ");
            String nome = new Scanner(System.in).nextLine();
            System.out.print("Informe a quantidade: ");
            int quantidade = scanner.nextInt();
            for ( TipoMedida tipo : TipoMedida.values() ) {
                System.out.printf("%d - %s\n", tipo.ordinal(), tipo.name());
            }
            int tipo = scanner.nextInt();
            ingredientes.add( new Ingrediente(nome, quantidade, TipoMedida.values()[tipo]) );

            System.out.print("Deseja inserir outro ingrediente (s/n)?: ");
            op = scanner.next();

        } while ( op.toLowerCase().equals("s") );

        return ingredientes;
    }

}
