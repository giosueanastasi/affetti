package it.pittysoft.affetti.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QContratti is a Querydsl query type for Contratti
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QContratti extends EntityPathBase<Contratti> {

    private static final long serialVersionUID = 1180262285L;

    public static final QContratti contratti = new QContratti("contratti");

    public final StringPath data_inizio = createString("data_inizio");

    public final StringPath data_insert = createString("data_insert");

    public final StringPath data_scadenza = createString("data_scadenza");

    public final StringPath data_update = createString("data_update");

    public final StringPath fk_domanda_disposizione = createString("fk_domanda_disposizione");

    public final StringPath fk_domanda_loculo = createString("fk_domanda_loculo");

    public final StringPath fk_user_modifier = createString("fk_user_modifier");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath protocollo = createString("protocollo");

    public final StringPath stato = createString("stato");

    public QContratti(String variable) {
        super(Contratti.class, forVariable(variable));
    }

    public QContratti(Path<? extends Contratti> path) {
        super(path.getType(), path.getMetadata());
    }

    public QContratti(PathMetadata metadata) {
        super(Contratti.class, metadata);
    }

}

