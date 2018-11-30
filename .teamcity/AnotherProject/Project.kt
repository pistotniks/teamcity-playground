package AnotherProject
import AnotherProject.BuildTypes.RunBuild
import jetbrains.buildServer.configs.kotlin.v2018_1.*
import jetbrains.buildServer.configs.kotlin.v2018_1.Project

object Project : Project ({
    uuid = "960c326c-bf30-42ec-8429-9357908f927e"
    id("AnotherProject")
    parentId("RootProjectId_Teamcity_God")
    name = "AnotherProject"
    description = "Run another project"


    buildType(RunBuild)
    buildTypesOrder = arrayListOf(RunBuild)
})