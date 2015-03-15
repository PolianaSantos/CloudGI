# CloudGI
Para execultar o CloudGI é necessário seguir as instruções abaixo:

1 - Instale em uma computador ou maquina virtual VMware Player o sistema operacional Ubuntu Server 14.04 (Trusty).
- Em maquinas virtuais é necessário configurar o hadware com as seguintes especificações:
* 2GB de RAM;
* Processador Virtualize Intel VT-x/EPT or AMD-V/RVI;
* Rede modo bridge;

2 - Depois de instalar o sistema operacional siga os passos para instalar o openstack com o script da devstack que esta na página: http://docs.openstack.org/developer/devstack/.
* Como usuário e senha da máquina é recomendável colocar Usuário: devstack e senha: openstack123. Isto porque estes, estão configurados na aplicação para acesso ao ssh do linux. Caso sejá necessário a mudanças dos mesmo, acessar as classes MPInfoUser.java e ServerUser.java para modificar;
* Após a instalação do openstack, será gerado um endereço de IP. Esse endereço de IP deve ser inserido na variável host da classe ServerUser.java;
* É necessário também gerar o par de chaves do servidor: Para isso é necessário execultar o comando: source openrc admin admin para se autenticar no serviço openstack e logo em seguida: nova keypair-add KeyPair01 > MY_KEY.pem para gerar o par de chaves.

3 - 
