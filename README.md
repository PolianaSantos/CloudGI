# CloudGI

Para executar o CloudGI é necessário seguir as instruções abaixo:

1 - Instale em uma computador ou maquina virtual VMware Player o sistema operacional Ubuntu Server 14.04 (Trusty).
- Em maquinas virtuais é necessário configurar o hardware com as seguintes especificações:
* 2GB de RAM;
* Processador Virtualize Intel VT-x/EPT or AMD-V/RVI;
* Rede modo bridged;

2 - Depois de instalar o sistema operacional siga os passos para instalar o openstack com o script da devstack que esta na página: http://docs.openstack.org/developer/devstack/.
* Como usuário e senha da máquina é recomendável colocar Usuário: devstack e senha: openstack123. Isto porque estes, estão configurados na aplicação para acesso ao ssh do Linux. Caso seja necessário mudanças dos mesmos, acessar as classes MPInfoUser.java e ServerUser.java para modificar;
* Após a instalação do openstack, será gerado um endereço de IP. Esse endereço de IP deve ser inserido na variável host da classe ServerUser.java;
* É necessário também gerar o par de chaves do servidor: Para isso é necessário executar o comando: "source openrc admin admin", para se autenticar no serviço openstack e logo em seguida: "nova keypair-add KeyPair01 > MY_KEY.pem" para gerar o par de chaves.

3 - No diretório da aplicação contém um script para gerar as tabelas da base de dados. Para a construção da mesma foi utilizado a API JDBC e base de dados Apache Derby. Este serviço já está disponível na plataforma de desenvolvimento Netbeans. 
* Crie uma base de dados intitulada CloudGI;
* Usuário: devstack, Senha: openstack123. Isto porque estes, estão configurados na aplicação para acesso a base de dados;
* Execute o script para gerar as tabelas e inserir dados necessários para o funcionamento da aplicação.

4 - Para executar a aplicação existem duas formas:
* No ambiente de desenvolvimento integrado Netbeans abra os dois projetos: O CloudGI web e o desktop
- Execute primeiro o CloudGI web para iniciar o glassfish e o serviço de base de dados que também será utilizado pelo CloudGI Desktop;
- Cadastre um usuário, inicie um serviço para o mesmo;
- Depois execute o CloudGI-Desktop. Clique no botão "Iniciar monitoramento" para visualizar e iniciar o monitoramneto das réplicas. 

* A  outra forma de é executando os arquivos war e jar clicando ou executando os comando abaixo
- Para a aplicação é necessário implantar a aplicação no GlassFish usando o seguinte comando: "asadmin deploy endereço_do_diretório_que_foi_salvo_claoudGI\dist\CloudGI.war" e depois acessar: http://localhost:8080/CloudGI/faces/Paginas/index.xhtml
- Execute o seguinte comando: java -jar "endereço_do_diretório_que_foi_salvo_claoudGI\CloudGI_Desktop\dist\CloudGI.jar"
Obs.: Para essa opção é necessario instalar e iniciar o glassfissh e o serviço de banco de dados 

5 - A aplicação web apresenta as seguintes funcionalidades:
* Casdastrar usuário;
* Cadastrar serviço: nesta opção, um serviço é iniciado passando como parâmetros: nome do grupo de instâncias, tipo de falha, serviço, imagem e flavor;
* Monitorar as réplicas instanciadas;
* Excluir um serviço.

6 - A aplicação desktop apresenta as seguintes funcionalidades:
* Iniciar monitoramento de instâncias;
* Excluir uma instância;
* Listar instâncias no servidor;
* Reiniciar uma instância;
* Criar uma instância.

7 - Para fins de teste, seria interessante, reiniciar, pausar, arquivar, suspender e desligar as instâncias. Para isso utilize os seguintes comandos no terminal do Servidor linux que está instalado o openstack:
$ nova reboot nomeInstancia;
$ nova pause nomeInstancia;
$ nova shelve nomeInstancia;
$ nova suspende nomeInstancia;
$ nova stop nomeInstancia.

Obs.: O serviço da openstack instalado pelo script da devstack, só permite instanciar no máximo 10 réplicas. É necessário iniciar o serviço de base de dados para executar o CloudGI-Desktop, para isso executar primeiro a aplicação web que a mesma inicia.
