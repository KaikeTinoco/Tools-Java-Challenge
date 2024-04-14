package br.com.desafiotools.model.enums;

public enum Status {

    APROVADO("aprovado"),
    RECUSADO("recusado");

    private String descricao;
    Status(String descricao){
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
