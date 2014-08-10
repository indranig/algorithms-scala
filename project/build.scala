import sbt._
import Keys._


object BuildSettings {
  val buildProject      = "algorithms-scala"
  val buildOrganization = "org.awong"
  val buildVersion      = "0.0.1-SNAPSHOT"
  val buildScalaVersion = "2.10.1"
  val javaVersion       = "1.7"

  val buildSettings = Defaults.defaultSettings ++ Seq (
    organization  := buildOrganization,
    version       := buildVersion,
    scalaVersion  := buildScalaVersion,
    scalacOptions ++= Seq("-unchecked", "-deprecation"),
    javacOptions  ++= Seq("-target", javaVersion, "-source", javaVersion),
    shellPrompt  := ShellPrompt.buildShellPrompt,
    licenses := Seq("MIT" -> url("http://opensource.org/licenses/MIT")),
    pomExtra := (
      <scm>
        <url>git@github.com:algorithms-scala/reboot.git</url>
        <connection>scm:git:git@github.com:algorithms-scala/reboot.git</connection>
      </scm>
      <developers>
        <developer>
          <id>awong</id>
          <name>Alan Wong</name>
          <url>http://www.github.com/alanktwong</url>
        </developer>
      </developers>)
  )
}

// Shell prompt which show the current project,
// git branch and build version
object ShellPrompt {
  object devnull extends ProcessLogger {
    def info (s: => String) {}
    def error (s: => String) { }
    def buffer[T] (f: => T): T = f
  }
  def currBranch = (
    ("git status -sb" lines_! devnull headOption)
      getOrElse "-" stripPrefix "## "
  )

  val buildShellPrompt = {
    (state: State) => {
      val currProject = Project.extract (state).currentProject.id
      "%s:%s:%s> ".format (
        currProject, currBranch, BuildSettings.buildVersion
      )
    }
  }
}

object Resolvers {
  val sunRepo    = "Sun Maven2 Repo"    at "http://download.java.net/maven/2"
  val sunRepoGF  = "Sun GF Maven2 Repo" at "http://download.java.net/maven/glassfish"
  val oracleRepo = "Oracle Maven2 Repo" at "http://download.oracle.com/maven"

  val oracleResolvers    = Seq(sunRepo, sunRepoGF, oracleRepo)
  
  val typesafeRepo   = "Akka Repo" at "http://repo.typesafe.com/typesafe/releases/"

  val springReleaseRepo           = "EBR Spring Release Repository" at "http://repository.springsource.com/maven/bundles/release"
  val springExternalReleaseRepo   = "EBR Spring External Release Repository" at "http://repository.springsource.com/maven/bundles/external"
  val springMilestoneRepo         = "Spring Milestone Repository" at "https://repo.springsource.org/libs-milestone"

  val jBossRepo = "JBoss Public Maven Repository Group" at "https://repository.jboss.org/nexus/content/groups/public-jboss/"

  val springResolvers    = Seq(springReleaseRepo, springExternalReleaseRepo)

  val allResolvers = Seq(typesafeRepo, jBossRepo) ++ oracleResolvers ++ springResolvers
}

object Versions {
  lazy val javaVersion        = BuildSettings.javaVersion
  lazy val scalaVersion       = BuildSettings.buildScalaVersion
  lazy val twitterUtilVersion = "6.12.1"
  lazy val slf4jVersion       = "1.6.4"
  lazy val scalaTestVersion   = "2.0"
}


object Dependencies {
  import Versions._

  lazy val provided = "provided"
  lazy val test     = "test"
  lazy val runtime  = "runtime"

  lazy val junit      = "junit"          %  "junit"        %  "4.10"           % test
  lazy val mockito    = "org.mockito"    %  "mockito-core" %  "1.9.5"          % test
  lazy val scalaTest  = "org.scalatest"  %% "scalatest"    %  scalaTestVersion % test
  lazy val scalaCheck = "org.scalacheck" %% "scalacheck"   %  "1.10.1"         % test
  lazy val testDependencies = Seq(junit, scalaTest, scalaCheck, mockito)

  lazy val twitterOrg            = "com.twitter"
  lazy val twitterApp            = twitterOrg    %% "util-app"               % twitterUtilVersion
  lazy val twitterBenchmark      = twitterOrg    %% "util-benchmark"         % twitterUtilVersion
  lazy val twitterClassPreloader = twitterOrg    %% "util-class-preloader"   % twitterUtilVersion
  lazy val twitterCodec          = twitterOrg    %% "util-codec"             % twitterUtilVersion
  lazy val twitterCollection     = twitterOrg    %% "util-collection"        % twitterUtilVersion
  lazy val twitterCore           = twitterOrg    %% "util-core"              % twitterUtilVersion
  lazy val twitterEval           = twitterOrg    %% "util-eval"              % twitterUtilVersion
  lazy val twitterHashing        = twitterOrg    %% "util-hashing"           % twitterUtilVersion
  lazy val twitterJvm            = twitterOrg    %% "util-jvm"               % twitterUtilVersion
  lazy val twitterLogging        = twitterOrg    %% "util-logging"           % twitterUtilVersion
  lazy val twitterReflect        = twitterOrg    %% "util-reflect"           % twitterUtilVersion
  lazy val twitterThrift         = twitterOrg    %% "util-thrift"            % twitterUtilVersion
  lazy val twitterZkCommon       = twitterOrg    %% "util-zk-common"         % twitterUtilVersion
  lazy val twitterZk             = twitterOrg    %% "util-zk"                % twitterUtilVersion

  lazy val twitterUtilDependencies = Seq(twitterCore, twitterBenchmark, twitterEval)
  
  lazy val commonsIO             = "commons-io"  % "commons-io"              % "2.4"
  lazy val apacheCommonsDependencies = Seq(commonsIO)

  lazy val slf4j       = "org.slf4j" % "slf4j-api"      % slf4jVersion
  lazy val slf4jSimple = "org.slf4j" % "slf4j-simple "  % slf4jVersion
  lazy val slf4jlog4j  = "org.slf4j" % "slf4j-log4j12"  % slf4jVersion
  lazy val slf4jDependencies       = Seq(slf4j, slf4jlog4j)
  
  lazy val scalazFull     = "org.scalaz"             %% "scalaz-full"  %  "6.0.4"
  lazy val typesafeConfig = "com.typesafe"           %  "config"       %  "1.2.1"
  lazy val rxScala        = "com.netflix.rxjava"     %  "rxjava-scala" %  "0.15.0"
  lazy val scalaAsync     = "org.scala-lang.modules" %% "scala-async"  %  "0.9.0-M2"
  lazy val scalaSwing     = "org.scala-lang"         %  "scala-swing"  %  scalaVersion

  lazy val stdlibDependencies      = Seq(scalaSwing, typesafeConfig, rxScala, scalaAsync) ++ testDependencies ++ slf4jDependencies ++ apacheCommonsDependencies
  lazy val fundamentalDependencies = stdlibDependencies
  lazy val sortingDependencies     = stdlibDependencies
  lazy val searchingDependencies   = stdlibDependencies
  lazy val graphsDependencies      = stdlibDependencies
  lazy val stringsDependencies     = stdlibDependencies
  lazy val contextDependencies     = stdlibDependencies
  lazy val beyondDependencies      = stdlibDependencies
}

object ProjectBuild extends Build {
  import Resolvers._
  import Dependencies._
  import BuildSettings._

  // Sub-project specific dependencies
  lazy val all = Project (
    id = buildProject + "-all",
    base = file ("."),
    settings = buildSettings ++ Seq(
      resolvers ++= allResolvers,
      description := "Wraps up all the modules"
    )
  ) aggregate (
    beyond,
    fundamentals,
    context,
    graphs,
    searching,
    sorting,
    stdlib,
    strings
  )

  lazy val stdlib = Project (
    id = buildProject + "-stdlib",
    base = file ("stdlib"),
    settings = buildSettings ++ Seq (
      resolvers ++= allResolvers,
      libraryDependencies ++= stdlibDependencies,
      description := "The core module"
    )
  )

  lazy val fundamentals = Project (
    buildProject + "-fundamentals",
    file ("fundamentals"),
    settings = buildSettings ++ Seq (
      resolvers ++= allResolvers,
      libraryDependencies ++= fundamentalDependencies,
      description := "The module for fundamentals"
    )
  ) dependsOn (
    stdlib % "compile;test->test;provided->provided"
  )

  lazy val sorting = Project (
    buildProject + "-sorting",
    file ("sorting"),
    settings = buildSettings ++ Seq (
      resolvers ++= allResolvers,
      libraryDependencies ++= sortingDependencies,
      description := "The module for sorting"
    )
  ) dependsOn (
    fundamentals % "compile;test->test;provided->provided",
    stdlib % "compile;test->test;provided->provided"
  )

  lazy val searching = Project (
    buildProject + "-searching",
    file ("searching"),
    settings = buildSettings ++ Seq (
      resolvers ++= allResolvers,
      libraryDependencies ++= searchingDependencies,
      description := "The module for searching"
    )
  ) dependsOn (
    sorting % "compile;test->test;provided->provided",
    stdlib % "compile;test->test;provided->provided"
  )

  lazy val graphs = Project (
    buildProject + "-graphs",
    file ("graphs"),
    settings = buildSettings ++ Seq (
      resolvers ++= allResolvers,
      libraryDependencies ++= graphsDependencies,
      description := "The module for graphs"
    )
  ) dependsOn (
    searching % "compile;test->test;provided->provided",
    stdlib % "compile;test->test;provided->provided"
  )

  lazy val strings = Project (
    buildProject + "-strings",
    file ("strings"),
    settings = buildSettings ++ Seq (
      resolvers ++= allResolvers,
      libraryDependencies ++= stringsDependencies,
      description := "The module for strings"
    )
  ) dependsOn (
    graphs % "compile;test->test;provided->provided",
    stdlib % "compile;test->test;provided->provided"
  )

  lazy val context = Project (
    buildProject + "-context",
    file ("context"),
    settings = buildSettings ++ Seq (
      resolvers ++= allResolvers,
      libraryDependencies ++= contextDependencies,
      description := "The module for context"
    )
  ) dependsOn (
    graphs % "compile;test->test;provided->provided",
    stdlib % "compile;test->test;provided->provided"
  )

  lazy val beyond = Project (
    buildProject + "-beyond",
    file ("beyond"),
    settings = buildSettings ++ Seq (
      resolvers ++= allResolvers,
      libraryDependencies ++= beyondDependencies,
      description := "The module for beyond"
    )
  ) dependsOn (
    searching % "compile;test->test;provided->provided",
    stdlib % "compile;test->test;provided->provided"
  )
}