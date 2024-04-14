package br.com.desafiotools.model.enums;

public enum Tipo {

    AVISTA("AVISTA"),
    PARCELADO_LOJA("PARCELADO LOJA"),
    PARCELADO_EMISSOR("PARCELADO EMISSOR");

    private String descricao;
    Tipo(String descricao){
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
