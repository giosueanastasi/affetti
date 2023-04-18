package it.pittysoft.affetti.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAssegnatari is a Querydsl query type for Assegnatari
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAssegnatari extends EntityPathBase<Assegnatari> {

    private static final long serialVersionUID = 1530871583L;

    public static final QAssegnatari assegnatari = new QAssegnatari("assegnatari");

    public final StringPath cognome = createString("cognome");

    public final StringPath comune_decesso = createString("comune_decesso");

    public final StringPath data_decesso = createString("data_decesso");

    public final StringPath data_insert = createString("data_insert");

    public final StringPath data_update = createString("data_update");

    public final SetPath<Domande, QDomande> domande = this.<Domande, QDomande>createSet("domande", Domande.class, QDomande.class, PathInits.DIRECT2);

    public final StringPath fk_user_modifier = createString("fk_user_modifier");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath nome = createString("nome");

    public QAssegnatari(String variable) {
        super(Assegnatari.class, forVariable(variable));
    }

    public QAssegnatari(Path<? extends Assegnatari> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAssegnatari(PathMetadata metadata) {
        super(Assegnatari.class, metadata);
    }

}

