package com.gachon.frimo.domain.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.stereotype.Component;

import com.gachon.frimo.domain.BaseTimeEntity;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "model")
@Component
public class Model extends BaseTimeEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "model_pk")
    private Long modelPk;

    @Column(length = 45, name = "model_name")
    private String modelName;

    @Column(length = 45, name = " model_path")
    private String modelPath;

    @Builder
    public Model(String modelName, String modelPath) {
        this.modelName = modelName;
        this.modelPath = modelPath;
    }

}
