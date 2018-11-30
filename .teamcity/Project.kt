import jetbrains.buildServer.configs.kotlin.v2018_1.*
import jetbrains.buildServer.configs.kotlin.v2018_1.Project

object Project : Project ({
    uuid = "9814b870-7d53-4e11-a623-f5ee025df33a"
    //id ("RootProjectId_Teamcity_God")
    //name = "RootProjectId_Teamcity_God"
    description = "Teamcity God"

    subProjectsOrder = arrayListOf(AbsoluteId("AnotherProject"))
})