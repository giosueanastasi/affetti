package it.pittysoft.affetti.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDomande is a Querydsl query type for Domande
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QDomande extends EntityPathBase<Domande> {

    private static final long serialVersionUID = 1526343957L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDomande domande = new QDomande("domande");

    public final QAssegnatari assegnatario;

    public final StringPath data_insert = createString("data_insert");

    public final StringPath data_protocollo = createString("data_protocollo");

    public final StringPath data_update = createString("data_update");

    public final NumberPath<Long> fk_contraente = createNumber("fk_contraente", Long.class);

    public final NumberPath<Long> fk_user_modifier = createNumber("fk_user_modifier", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QPosti posto;

    public final StringPath protocollo = createString("protocollo");

    public final StringPath stato = createString("stato");

    public QDomande(String variable) {
        this(Domande.class, forVariable(variable), INITS);
    }

    public QDomande(Path<? extends Domande> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDomande(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDomande(PathMetadata metadata, PathInits inits) {
        this(Domande.class, metadata, inits);
    }

    public QDomande(Class<? extends Domande> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.assegnatario = inits.isInitialized("assegnatario") ? new QAssegnatari(forProperty("assegnatario")) : null;
        this.posto = inits.isInitialized("posto") ? new QPosti(forProperty("posto")) : null;
    }

}

