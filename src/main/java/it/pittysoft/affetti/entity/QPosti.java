package it.pittysoft.affetti.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPosti is a Querydsl query type for Posti
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPosti extends EntityPathBase<Posti> {

    private static final long serialVersionUID = -353803154L;

    public static final QPosti posti = new QPosti("posti");

    public final StringPath data_insert = createString("data_insert");

    public final StringPath data_update = createString("data_update");

    public final SetPath<Domande, QDomande> domande = this.<Domande, QDomande>createSet("domande", Domande.class, QDomande.class, PathInits.DIRECT2);

    public final StringPath fk_user_modifier = createString("fk_user_modifier");

    public final StringPath fornice = createString("fornice");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath loculo = createString("loculo");

    public final StringPath tipo = createString("tipo");

    public QPosti(String variable) {
        super(Posti.class, forVariable(variable));
    }

    public QPosti(Path<? extends Posti> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPosti(PathMetadata metadata) {
        super(Posti.class, metadata);
    }

}

