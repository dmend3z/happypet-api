package com.happypet.HappyPet.persistence.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import java.io.Serializable;
import java.util.Date;

@Data
@MappedSuperclass
public class AbstractEntity implements Serializable {


    @Version
    private int version;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date CreatedAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateAt;
}
