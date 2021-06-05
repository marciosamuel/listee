package com.dispositivos.moveis.listee;

import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

public class Produto {
    CardView container;
    ImageView imagem;
    TextView nome;
    TextView quantidade;
    TextView unidade;
    ImageButton aumentarQuantidade;
    ImageButton diminuirQuantidade;
    ImageButton removerProduto;

    public void setContainer(CardView container) {
        this.container = container;
    }

    public void setImagem(ImageView imagem) {
        this.imagem = imagem;
    }

    public void setNome(TextView nome) {
        this.nome = nome;
    }

    public void setQuantidade(TextView quantidade) {
        this.quantidade = quantidade;
    }

    public void setUnidade(TextView unidade) {
        this.unidade = unidade;
    }

    public void setAumentarQuantidade(ImageButton aumentarQuantidade) {
        this.aumentarQuantidade = aumentarQuantidade;
    }

    public void setDiminuirQuantidade(ImageButton diminuirQuantidade) {
        this.diminuirQuantidade = diminuirQuantidade;
    }

    public void setRemoverProduto(ImageButton removerProduto) {
        this.removerProduto = removerProduto;
    }
}
