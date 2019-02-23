if [ ! -d spigot ];then
  mkdir spigot
  cd spigot
  curl -o BuildTools.jar https://hub.spigotmc.org/jenkins/job/BuildTools/lastSuccessfulBuild/artifact/target/BuildTools.jar
  java -jar BuildTools.jar
  cd -
fi

bash build.sh
cd spigot
rm -rf world
rm -rf world_nether
rm -rf world_the_end
rm -rf plugins/Terraform/*
java -jar spigot-1.13.2.jar
