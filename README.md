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

4 - Execute a aplicação web, cadastre um usuário, inicie um serviço para o mesmo.

5 - Execute a aplicação desktop e clique em iniciar monitoramento.

Obs.: O serviço da openstack instalado pelo script da devstack, só permite instanciar no máximo 10 réplicas. É necessário iniciar o serviço de base de dados para executar o CloudGI-Desktop, para isso executar primeiro a aplicação web que a mesma inicia.
