
![reconhecimento-facial-banner](https://github.com/git-jr/3799-Reconhecimento-Facial-Android-MLKit/assets/35709152/871df7bb-04b7-4b1e-aa3d-523f4bb4f6af)
![](https://img.shields.io/github/license/alura-cursos/android-com-kotlin-personalizando-ui)

# SorrIA
É um app que simula o funcionamento de uma rede social capaz de exibir fotos e vídeos. A fim de explorar as possibilidades do reconhecimento facial por meio do Google ML Kit e tornar o app mais acessível, algumas funções especiais serão adicionadas a ele.


## 🔨 Funcionalidades do projeto

### 🧑Reconhcimento facial
- ❤️ Sorrir dispara o "like" no post mais visível na tela e exibe a animação de coração pulsando.
- 🛞Mover a cabeça em um determinado ângulo desliza o feed de posts para cima ou para baixo.
- 🤯 A navegação por voz é ativada quando a ação de abrir a boca ou piscar um dos olhos é efetuada, isso permite ir para qualquer tela do app sem precisar tocá-lo.



https://github.com/git-jr/3799-Reconhecimento-Facial-Android-MLKit/assets/35709152/d5f4a6dc-c85f-4ffb-b27b-f07570f195a5


### 📱Telas:
- Aba Início: Lista os posts que contêm o conteúdo e um carrossel de imagens. É possível curtir o post clicando no ícone de coração ou dando dois toques rápidos na tela.
- Aba Vídeos: Simula a tela de exibição de vídeos vertical, comum em apps de vídeos curtos.
- Abas Buscar e Perfil: Telas de amostra para usar o recurso de navegação.
- Câmera Preview: Demonstração dos valores de algumas propriedades obtidas pelo modelo ao detectar um rosto.


https://github.com/git-jr/3799-Reconhecimento-Facial-Android-MLKit/assets/35709152/cece5415-feaf-49c9-be15-e5e7eded6d7f





## ✔️ Técnicas e tecnologias utilizadas

As técnicas e tecnologias utilizadas pra isso são:

- `Jetpack Compose`: kit de ferramentas moderno para criar IUs em dispositivos móveis
- `Kotlin`: linguagem de programação
- `Gradle Version Catalogs`: nova forma de gerenciar plugins e dependências em projetos Android
- `Material Design 3`: padrão de design recomendado pela google para criação de UI modernas
- `Hilt`: injeção de dependências
- `Navigating with Compose`: navegação entre composables e telas
- `Viewmodel, states e flow`: gerenciamento de estados da e controle dos eventos disparados pelas detecções do modelo da Google
- `ML Kit Face Detection `: biblioteca para detectar e extrair informações sobre rostos em tempo real usando a câmera do dispositivo
- `CameraX`: biblioteca do Jetpack que facilita a integração de funcionalidades de câmera em aplicativos Android, abstraindo a complexidade da API de câmera do Android e oferecendo uma interface simples para captura de fotos e vídeo
- `CameraAnalyzer`: componente utilizado junto ao CameraX para analisar frames de vídeo em tempo real, permitindo a implementação de funcionalidades como detecção de rostos, reconhecimento de gestos ou qualquer outra análise de imagem que requeira processamento frame a frame.


## 📁 Acesso ao projeto

- Versão inicial: Veja o [código fonte][codigo-inicial] ou [baixe o projeto][download-inicial]
- Versão final: Veja o [código fonte][codigo-final] ou [baixe o projeto][download-final]

## 🛠️ Abrir e rodar o projeto
Após baixar o projeto, você pode abri-lo com o Android Studio. Para isso, na tela de launcher clique em:

“Open” (ou alguma opção similar), procure o local onde o projeto está e o selecione (caso o projeto seja baixado via zip, é necessário extraí-lo antes de procurá-lo). Por fim, clique em “OK” o Android Studio deve executar algumas tasks do Gradle para configurar o projeto, aguarde até finalizar. Ao finalizar as tasks, você pode executar o App 🏆


## 📚 Mais informações do curso

Gostou do projeto e quer conhecer mais? Você pode [acessar o curso](https://www.alura.com.br/formacao-android-ia-google-ml-kit) que desenvolve o projeto desde o começo!

[codigo-inicial]: https://github.com/git-jr/3799-Reconhecimento-Facial-Android-MLKit/commits/aula-1
[download-inicial]: https://github.com/git-jr/3799-Reconhecimento-Facial-Android-MLKit/archive/refs/heads/aula-1.zip

[codigo-final]: https://github.com/git-jr/3799-Reconhecimento-Facial-Android-MLKit/commits/aula-5/
[download-final]: https://github.com/git-jr/3799-Reconhecimento-Facial-Android-MLKit/archive/refs/heads/aula-5.zip
