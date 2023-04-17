package it.pittysoft.affetti.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QContraenti is a Querydsl query type for Contraenti
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QContraenti extends EntityPathBase<Contraenti> {

    private static final long serialVersionUID = -2067027014L;

    public static final QContraenti contraenti = new QContraenti("contraenti");

    public final StringPath cap_residenza = createString("cap_residenza");

    public final StringPath civico_residenza = createString("civico_residenza");

    public final StringPath codice_fiscale = createString("codice_fiscale");

    public final StringPath cognome = createString("cognome");

    public final StringPath comune_nascita = createString("comune_nascita");

    public final StringPath comune_residenza = createString("comune_residenza");

    public final StringPath data_insert = createString("data_insert");

    public final StringPath data_nascita = createString("data_nascita");

    public final StringPath data_update = createString("data_update");

    public final StringPath email = createString("email");

    public final NumberPath<Long> fk_user_modifier = createNumber("fk_user_modifier", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath nome = createString("nome");

    public final StringPath note = createString("note");

    public final StringPath provincia_nascita = createString("provincia_nascita");

    public final StringPath provincia_residenza = createString("provincia_residenza");

    public final StringPath stato_nascita = createString("stato_nascita");

    public final StringPath telefono = createString("telefono");

    public final StringPath via_residenza = createString("via_residenza");

    public QContraenti(String variable) {
        super(Contraenti.class, forVariable(variable));
    }

    public QContraenti(Path<? extends Contraenti> path) {
        super(path.getType(), path.getMetadata());
    }

    public QContraenti(PathMetadata metadata) {
        super(Contraenti.class, metadata);
    }

}

