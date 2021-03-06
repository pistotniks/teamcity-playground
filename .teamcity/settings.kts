import jetbrains.buildServer.configs.kotlin.v2018_1.*
import jetbrains.buildServer.configs.kotlin.v2018_1.buildSteps.script
import jetbrains.buildServer.configs.kotlin.v2018_1.triggers.vcs

/*
The settings script is an entry point for defining a TeamCity
project hierarchy. The script should contain a single call to the
project() function with a Project instance or an init function as
an argument.

VcsRoots, BuildTypes, Templates, and subprojects can be
registered inside the project using the vcsRoot(), buildType(),
template(), and subProject() methods respectively.

To debug settings scripts in command-line, run the

    mvnDebug org.jetbrains.teamcity:teamcity-configs-maven-plugin:generate

command and attach your debugger to the port 8000.

To debug in IntelliJ Idea, open the 'Maven Projects' tool window (View
-> Tool Windows -> Maven Projects), find the generate task node
(Plugins -> teamcity-configs -> teamcity-configs:generate), the
'Debug' option is available in the context menu for the task.
*/

version = "2018.1"
project (Project)
//project {
//    uuid = "9814b870-7d53-4e11-a623-f5ee025df33a"
//    description = "Something else"
//
//    buildType(Build)
//
//    subProjectsOrder = arrayListOf(AbsoluteId("Build"))
//}

object Build : BuildType({
    name = "Build"

    steps {
        script {
            name = "set version using script and not powershell cause i have linux container"
            scriptContent = """
                #!/bin/bash
                HASH=%build.vcs.number%
                SHORT_HASH=${"$"}{HASH:0:7}
                BUILD_COUNTER=%build.counter%
                BUILD_NUMBER="1.0.${"$"}BUILD_COUNTER.${"$"}SHORT_HASH"
                echo "##teamcity[buildNumber '${"$"}BUILD_NUMBER']"
            """.trimIndent()
        }
        script {
            name = "build"
            scriptContent = """
                mkdir bin
                echo "built artifact" > bin/compiled.txt
            """.trimIndent()
        }
    }
    vcs {
        root(DslContext.settingsRoot)
    }

    triggers {
        vcs {
        }
    }
})
