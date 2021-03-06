package br.com.matteroftime.models;

import java.io.Serializable;
import java.util.List;


import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;

/**
 * Created by RedBlood on 30/03/2017.
 */

public class Musica extends RealmObject implements Serializable {

    @PrimaryKey
    private long id;
    private String nome;
    private RealmList<Compasso> compassos;
    private int ordem;
    private boolean possuiOrdem = false;
    private int qtdCompassos;
    private boolean preContagem;
    private int temposContagem;
    private boolean compasso = false;
    @Ignore
    private List<Compasso> compassosList;


    public Musica() {
    }

    public Musica(long id, String nome, RealmList<Compasso> compassos, int ordem,
                  boolean possuiOrdem,int qtdCompassos, boolean preContagem, int temposContagem,
                  boolean compasso, List<Compasso> compassosList) {
        this.id = id;
        this.nome = nome;
        this.compassos = compassos;
        this.ordem = ordem;
        this.possuiOrdem = possuiOrdem;
        this.qtdCompassos = qtdCompassos;
        this.preContagem = preContagem;
        this.temposContagem = temposContagem;
        this.compasso = compasso;
        this.compassosList = compassosList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public RealmList<Compasso> getCompassos() {
        return compassos;
    }

    public void setCompassos(RealmList<Compasso> compassos) {
        this.compassos = compassos;
    }

    public int getOrdem() {
        return ordem;
    }

    public void setOrdem(int ordem) {
        this.ordem = ordem;
    }

    public boolean isPossuiOrdem() {
        return possuiOrdem;
    }

    public void setPossuiOrdem(boolean possuiOrdem) {
        this.possuiOrdem = possuiOrdem;
    }

    public int getQtdCompassos() {
        return qtdCompassos;
    }

    public void setQtdCompassos(int qtdCompassos) {
        this.qtdCompassos = qtdCompassos;
    }

    public boolean isPreContagem() {
        return preContagem;
    }

    public void setPreContagem(boolean preContagem) {
        this.preContagem = preContagem;
    }

    public int getTemposContagem() {
        return temposContagem;
    }

    public void setTemposContagem(int temposContagem) {
        this.temposContagem = temposContagem;
    }

    public boolean isCompasso() {
        return compasso;
    }

    public void setCompasso(boolean compasso) {
        this.compasso = compasso;
    }

    public List<Compasso> getCompassosList() {
        return compassosList;
    }

    public void setCompassosList(List<Compasso> compassosList) {
        this.compassosList = compassosList;
    }

    public void defineIntervalo(final RealmList<Compasso> compassos) {
        for (Compasso compasso : compassos) {
            switch (compasso.getNota()){

                //semi-breve
                case 1:
                    compasso.setIntervalo((1.0 / compasso.getBpm() * 4 ) * 60000.0);
                    break;
                //mínima
                case 2:
                    compasso.setIntervalo((1.0 / compasso.getBpm() * 2 ) * 60000.0);
                    break;
                //semínima
                case 4:
                    compasso.setIntervalo(1.0 / compasso.getBpm() * 60000.0);
                    break;
                //colcheia
                case 8:
                    compasso.setIntervalo((1.0 / compasso.getBpm() / 2 ) * 60000.0);
                    break;
                //semicolcheia
                case 16:
                    compasso.setIntervalo((1.0 / compasso.getBpm() / 4 ) * 60000.0);
                    break;
                //fusa
                case 32:
                    compasso.setIntervalo((1.0 / compasso.getBpm() / 8 ) * 60000.0);
                    break;
                //semifusa
                case 64:
                    compasso.setIntervalo((1.0 / compasso.getBpm() / 16 ) * 60000.0);
                    break;
            }
        }

    }

}
