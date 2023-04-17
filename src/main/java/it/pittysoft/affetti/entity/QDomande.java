package it.pittysoft.affetti.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QDomande is a Querydsl query type for Domande
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDomande extends EntityPathBase<Domande> {

    private static final long serialVersionUID = 1526343957L;

    public static final QDomande domande = new QDomande("domande");

    public final StringPath data_insert = createString("data_insert");

    public final StringPath data_protocollo = createString("data_protocollo");

    public final StringPath data_update = createString("data_update");

    public final NumberPath<Long> fk_assegnatario = createNumber("fk_assegnatario", Long.class);

    public final NumberPath<Long> fk_contraente = createNumber("fk_contraente", Long.class);

    public final NumberPath<Long> fk_posto = createNumber("fk_posto", Long.class);

    public final NumberPath<Long> fk_user_modifier = createNumber("fk_user_modifier", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath protocollo = createString("protocollo");

    public final StringPath stato = createString("stato");

    public QDomande(String variable) {
        super(Domande.class, forVariable(variable));
    }

    public QDomande(Path<? extends Domande> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDomande(PathMetadata metadata) {
        super(Domande.class, metadata);
    }

}

