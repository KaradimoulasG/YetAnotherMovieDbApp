package com.example.yetanothermoviedbapp.data.mappers

interface Mapper<Model, DomainModel> {
    fun modelToDomain(model: Model): DomainModel

    fun domainToModel(domainModel: DomainModel): Model

    fun mapModelLists(domainList: List<DomainModel>): List<Model> {
        return domainList.mapNotNull {
            it?.let {
                domainToModel(it)
            }
        }
    }

    fun mapDomainLists(modelList: List<Model>): List<DomainModel> {
        return modelList.mapNotNull {
            it?.let {
                modelToDomain(it)
            }
        }
    }
}