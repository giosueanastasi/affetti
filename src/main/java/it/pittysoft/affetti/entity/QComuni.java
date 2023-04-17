package it.pittysoft.affetti.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QComuni is a Querydsl query type for Comuni
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QComuni extends EntityPathBase<Comuni> {

    private static final long serialVersionUID = 1544647626L;

    public static final QComuni comuni = new QComuni("comuni");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath nome = createString("nome");

    public final StringPath provincia = createString("provincia");

    public QComuni(String variable) {
        super(Comuni.class, forVariable(variable));
    }

    public QComuni(Path<? extends Comuni> path) {
        super(path.getType(), path.getMetadata());
    }

    public QComuni(PathMetadata metadata) {
        super(Comuni.class, metadata);
    }

}

