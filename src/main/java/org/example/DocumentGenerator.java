package org.example;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DocumentGenerator {
    private static final String DIRECTORY = "src/files/documents/";

    public static void main(String[] args) {
        File dir = new File(DIRECTORY);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String[] documents = {
                // Respostas para "quantas notas estão presentes em um piano padrão?"
                "O piano padrão possui 88 teclas, o que corresponde a 52 notas brancas e 36 notas pretas. Estas notas cobrem 7 oitavas completas e mais uma nota na oitava inferior, proporcionando uma ampla gama para a execução de diferentes estilos musicais.",

                // Respostas para "quero comprar um <instrumento>"
                "Comprar um violão é uma excelente escolha para quem está começando na música. Ele é um dos instrumentos mais versáteis e populares, adequado para uma variedade de estilos, incluindo pop, rock, e música folclórica.",
                "Se você está pensando em comprar um piano, esteja preparado para investir em um instrumento que oferece uma rica gama de expressividade e técnica. Ideal para quem deseja se aprofundar na música clássica e na teoria musical.",
                "Optar por um teclado é uma escolha prática, especialmente se você deseja explorar diferentes timbres e sons sem a necessidade de um espaço grande. É um instrumento versátil e perfeito para ensaios e apresentações ao vivo.",
                "Comprar uma guitarra é uma excelente escolha se você é fã de rock, blues, ou pop. Ela oferece uma vasta gama de sons e efeitos, além de permitir uma ampla gama de técnicas e estilos musicais.",
                "Se você está interessado em adquirir uma bateria, esteja pronto para se envolver com o ritmo e a percussão. A bateria é essencial para qualquer banda e é fundamental para criar o groove e a base rítmica da música.",
                "Uma flauta é ideal para quem busca um instrumento portátil e com um som suave e melodioso. Ela se adapta bem a uma ampla variedade de gêneros, incluindo música clássica e folclórica, sendo uma escolha encantadora.",
                "Um violino é uma escolha desafiadora, mas extremamente gratificante. Com sua expressividade e som lírico, é perfeito para quem deseja mergulhar profundamente no mundo da música clássica e em outros estilos emotivos.",
                "O saxofone é uma escolha apaixonante, especialmente para os fãs de jazz e blues. Com seu som quente e vibrante, ele é capaz de transmitir uma ampla gama de emoções e é perfeito para performances expressivas.",
                "O clarinete é uma excelente escolha para quem deseja um instrumento de sopro versátil. Ele se destaca tanto em orquestras quanto em bandas de jazz, oferecendo um som rico e suave que é apreciado em diversos contextos musicais.",
                "O trompete é uma escolha brilhante e potente, ideal para quem deseja se destacar em bandas de jazz, orquestras ou em qualquer gênero que exija um som forte e marcante. É um instrumento que com certeza vai chamar atenção.",

                // Respostas para "quero um <instrumento>"
                "Recomendo fortemente o violão para quem está começando no mundo da música. Ele é acessível, versátil e ideal para tocar uma grande variedade de gêneros. É um excelente primeiro instrumento para qualquer aspirante a músico.",
                "O piano é altamente recomendável para quem deseja uma base sólida em teoria musical e habilidades de performance. Ele é fundamental para aprender leitura de partituras e oferece uma expressividade inigualável em qualquer estilo.",
                "Recomendo o teclado para aqueles que buscam versatilidade e praticidade. Com ele, você pode experimentar uma ampla gama de sons e estilos, e é perfeito tanto para ensaios em casa quanto para apresentações ao vivo.",
                "A guitarra elétrica é uma recomendação sólida para qualquer pessoa interessada em rock, blues ou música pop. Ela oferece uma amplitude de sons e efeitos, sendo um instrumento extremamente expressivo e divertido de tocar.",
                "Recomendo a bateria para quem tem uma paixão por ritmo e deseja ser o coração de qualquer banda. Ela é essencial para criar o groove e a base rítmica que mantém a música viva e energizada.",
                "Recomendo a flauta para quem busca um som suave e melodioso em um instrumento portátil. É ideal para tocar música clássica e folclórica, e é uma ótima escolha para músicos de todas as idades.",
                "O violino é altamente recomendável para aqueles que buscam um som expressivo e emocional. Ele é um dos instrumentos mais desafiadores, mas também um dos mais gratificantes, oferecendo uma profundidade única em qualquer performance.",
                "Recomendo o saxofone para quem deseja um som quente e expressivo, especialmente em gêneros como jazz e blues. É um instrumento que permite uma grande liberdade na improvisação e na expressão pessoal.",
                "O clarinete é uma excelente recomendação para quem deseja um instrumento versátil e com um som rico. Ele é perfeito para tocar em orquestras, bandas de jazz ou como solista, oferecendo uma ampla gama de possibilidades musicais.",
                "Recomendo o trompete para quem deseja um som brilhante e poderoso, perfeito para se destacar em qualquer conjunto musical. É um instrumento com uma longa tradição em música clássica e jazz, conhecido por sua capacidade de liderar melodias.",

                // Respostas para "sim, quero começar com um <instrumento>"
                "Ótima escolha! Começar com um violão é uma excelente maneira de entrar no mundo da música. Ele é versátil, portátil e perfeito para tocar uma ampla variedade de estilos, desde pop até rock.",
                "Excelente escolha! O piano é uma base sólida para qualquer músico. Ele não apenas ensina teoria musical, mas também desenvolve habilidades de coordenação e leitura de partituras, essenciais para qualquer estilo musical.",
                "Ótima decisão! Começar com um teclado é uma escolha inteligente, especialmente pela variedade de sons e estilos que você pode explorar. Ele é portátil, acessível, e perfeito para tanto iniciantes quanto músicos mais avançados.",
                "Ótima escolha! A guitarra elétrica é divertida e extremamente versátil, ideal para quem quer explorar gêneros como rock, blues e pop. Com ela, você poderá desenvolver solos expressivos e aprender técnicas variadas.",
                "Ótima escolha! A bateria é fundamental para quem ama ritmo e quer ser o coração de uma banda. Começar com a bateria vai te dar uma base rítmica sólida e uma compreensão profunda do groove musical.",
                "Excelente escolha! A flauta é um instrumento melodioso e portátil, perfeito para quem busca um som doce e expressivo. Começar com a flauta te permitirá tocar uma ampla gama de músicas, desde clássicas até folclóricas.",
                "Ótima escolha! O violino é um dos instrumentos mais desafiadores e gratificantes. Ele oferece uma riqueza expressiva única, permitindo que você explore profundamente a música clássica e outros gêneros emotivos.",
                "Excelente escolha! O saxofone é um instrumento incrivelmente expressivo, perfeito para quem deseja explorar o jazz, blues, e até a música popular. Ele oferece uma amplitude emocional que poucos instrumentos podem igualar.",
                "Ótima escolha! O clarinete é um instrumento versátil, ideal para quem quer explorar tanto a música clássica quanto o jazz. Ele tem um som suave e rico, perfeito para uma variedade de estilos musicais.",
                "Ótima escolha! O trompete é um instrumento brilhante e poderoso, ideal para se destacar em bandas de jazz, orquestras, ou qualquer conjunto musical. Ele oferece uma expressividade vibrante que é difícil de igualar.",

                // Respostas para "quero informações sobre o <instrumento>"
                "O violão é um dos instrumentos mais populares e acessíveis, ideal para uma ampla gama de estilos musicais. Com suas cordas de nylon ou aço, ele é perfeito para tocar desde acordes simples até solos complexos.",
                "O piano é um dos instrumentos mais versáteis e expressivos, com suas 88 teclas permitindo uma ampla gama de dinâmicas e tonalidades. É essencial em muitos estilos musicais e oferece uma base sólida para o estudo da música.",
                "O teclado é uma excelente escolha para quem deseja explorar diferentes timbres e sons. Ele é mais compacto e acessível do que o piano, e é ideal para músicos que precisam de portabilidade e versatilidade.",
                "A guitarra elétrica é um dos pilares do rock e do blues, oferecendo uma ampla gama de sons e efeitos. Com seu amplificador e pedais, você pode criar uma variedade de timbres e estilos, tornando-a um instrumento muito dinâmico.",
                "A bateria é essencial para a base rítmica de qualquer grupo musical. Composta por tambores e pratos, ela define o groove e a energia da música. É um instrumento fundamental para desenvolver habilidades rítmicas e de coordenação.",
                "A flauta é um instrumento de sopro com um som leve e doce. É utilizada em muitos gêneros musicais, desde a música clássica até o folclore. É portátil e relativamente fácil de começar a tocar, sendo ideal para iniciantes.",
                "O violino é um instrumento de cordas tocado com um arco, conhecido por seu som lírico e expressivo. Ele é amplamente utilizado na música clássica e em outros gêneros, e é excelente para desenvolver habilidades técnicas e emocionais.",
                "O saxofone é um instrumento de sopro com um som quente e expressivo. É amplamente utilizado em jazz, blues, e música clássica, oferecendo uma ampla gama de expressividade e técnicas de improvisação.",
                "O clarinete é um instrumento de sopro com um som rico e versátil. É utilizado em música clássica, jazz e até mesmo em música popular. Ele é ideal para quem deseja um instrumento com uma ampla gama tonal e expressiva.",
                "O trompete é um instrumento de sopro de metal conhecido por seu som brilhante e poderoso. É essencial em muitos gêneros, especialmente em jazz e música clássica, e é conhecido por sua capacidade de criar melodias marcantes e vibrantes.",

                // Respostas para "quero aprender a tocar <gênero>"
                "Aprender a tocar rock envolve desenvolver habilidades na guitarra ou bateria, entender a estrutura de riffs e acordes característicos, e explorar a dinâmica energética típica deste gênero. O rock é conhecido por seu som poderoso e sua atitude irreverente.",
                "Para tocar jazz, você deve se concentrar em técnicas de improvisação, acordes complexos e ritmos sincopados. É importante estudar a teoria do jazz e ouvir os grandes mestres para entender a complexidade e a riqueza deste estilo musical.",
                "O estilo clássico exige um domínio técnico refinado e uma compreensão profunda das partituras. Praticar técnicas de interpretação e desenvolver uma sensibilidade musical são essenciais para executar obras de compositores como Bach, Mozart e Beethoven.",
                "Blues é um gênero que valoriza a expressividade e a improvisação. Aprender a tocar blues envolve entender as progressões de acordes típicas, os padrões de fraseado e a técnica do 'bend' para expressar emoção em cada nota.",
                "Para tocar pop, foque em técnicas de acordes simples, progressões melódicas e arranjos cativantes. O pop é conhecido por suas melodias memoráveis e por sua capacidade de se adaptar a uma ampla variedade de estilos e influências.",
                "O samba exige um bom senso de ritmo e coordenação. Aprender a tocar samba envolve dominar os padrões rítmicos característicos e a interação entre os diferentes instrumentos que formam a base percussiva deste gênero.",
                "Para tocar MPB (Música Popular Brasileira), é importante entender a riqueza harmônica e melódica dos compositores brasileiros. A MPB combina elementos de samba, bossa nova e outros estilos, exigindo sensibilidade musical e técnica apurada.",
                "A bossa nova é um estilo que combina ritmo suave com harmonias sofisticadas. Aprender a tocar bossa nova envolve dominar os padrões rítmicos do violão e os acordes complexos que caracterizam este gênero melódico e intimista.",
                "Para tocar eletrônica, você deve se familiarizar com a produção digital, programação de batidas e uso de sintetizadores. A eletrônica é um gênero que depende fortemente da tecnologia e da manipulação digital para criar seus sons inovadores.",
                "O folk é um gênero que valoriza a simplicidade e a autenticidade. Aprender a tocar folk envolve o uso de instrumentos acústicos, técnicas de dedilhado e a compreensão das histórias e temas narrativos comuns neste estilo musical.",

                // Respostas para "você pode começar estudando <método>"
                "Você pode começar estudando escalas para desenvolver uma base sólida no seu instrumento. As escalas ajudam a melhorar a técnica, a familiaridade com as notas e a capacidade de improvisação em diferentes tonalidades.",
                "Começar com acordes é uma excelente maneira de aprender a estrutura das músicas e a tocar progressões básicas. A compreensão dos acordes permitirá que você acompanhe canções e crie suas próprias composições.",
                "Estudar partituras é fundamental para a leitura e interpretação musical. Este método proporciona uma base sólida na teoria musical e permite que você toque uma ampla gama de peças em diferentes estilos.",
                "A improvisação é um método essencial para explorar a criatividade musical. Ao praticar improvisação, você aprende a criar melodias espontâneas e a se adaptar rapidamente a diferentes contextos musicais.",
                "Técnicas básicas são o alicerce para qualquer instrumento. Começar com fundamentos sólidos garante que você desenvolva habilidades essenciais como postura, dedilhado e controle de dinâmica desde o início do aprendizado.",

                // Respostas para "prefiro estudar <método>"
                "Prefiro estudar escalas, pois elas oferecem uma base técnica essencial para o desenvolvimento musical. Elas ajudam a entender a relação entre notas e acordes, e são fundamentais para a improvisação e a composição.",
                "Prefiro estudar acordes, pois eles são a base da harmonia e são cruciais para tocar e compor músicas. Entender a estrutura dos acordes é essencial para criar progressões harmônicas interessantes e acompanhar canções.",
                "Prefiro estudar partituras, pois elas proporcionam uma compreensão detalhada da música escrita. Isso ajuda a melhorar a leitura musical, a interpretação e a execução precisa de peças complexas em diferentes estilos.",
                "Prefiro estudar improvisação, pois permite explorar a criatividade e a expressão musical pessoal. A improvisação é uma habilidade valiosa que desenvolve a capacidade de reagir musicalmente e criar novas ideias instantaneamente.",
                "Prefiro estudar técnicas básicas, pois elas fornecem a fundação necessária para o progresso em qualquer instrumento. Técnicas básicas garantem que você tenha controle adequado e desenvolva uma execução sólida desde o início.",

                // Respostas para "meu gênero musical preferido é <gênero>"
                "Se seu gênero musical preferido é rock, você pode explorar a técnica de guitarra elétrica e a bateria com foco em riffs poderosos e solos expressivos. O rock é conhecido por sua energia e atitude, tornando-o ideal para performances energéticas.",
                "Se você gosta de jazz, concentre-se em improvisação e acordes complexos. O jazz valoriza a expressão pessoal e a criatividade, e é fundamental compreender a teoria do jazz para dominar este estilo musical dinâmico e sofisticado.",
                "Se seu gênero preferido é clássico, você deve estudar a interpretação de partituras e técnicas de performance refinadas. A música clássica é rica em complexidade e expressividade, exigindo uma abordagem detalhada e técnica para executar obras de grandes compositores.",
                "Se você prefere blues, pratique o uso de escalas pentatônicas e técnicas de 'bend' para expressar emoções. O blues é um gênero que enfatiza a expressão pessoal e a habilidade de transmitir sentimentos profundos através da música.",
                "Se sua preferência é pop, trabalhe em melodias cativantes e progressões de acordes simples. O pop é conhecido por suas músicas acessíveis e envolventes, e é importante dominar a criação de arranjos que capturem a atenção do ouvinte.",
                "Se você curte samba, foque no desenvolvimento de ritmos e padrões percussivos característicos. O samba é um gênero que valoriza o ritmo e a dança, e é essencial entender como criar grooves animados e envolventes.",
                "Se sua preferência é MPB, mergulhe na rica harmonia e na diversidade estilística dos compositores brasileiros. A MPB combina elementos variados, e é importante explorar as influências de samba, bossa nova e outros estilos.",
                "Se você gosta de bossa nova, pratique os acordes complexos e os padrões rítmicos suaves. A bossa nova é conhecida por sua elegância e sofisticação, e é fundamental dominar as nuances do estilo para tocar este gênero de forma autêntica.",
                "Se o gênero eletrônico é o seu preferido, estude a produção digital e o uso de sintetizadores. A eletrônica depende de tecnologia e criatividade para criar sons inovadores, e é essencial explorar software e técnicas de produção.",
                "Se você aprecia folk, concentre-se em técnicas de dedilhado e arranjos acústicos. O folk valoriza a simplicidade e a autenticidade, e é importante entender como contar histórias e criar melodias envolventes com instrumentos acústicos.",

                // Respostas para "você pode começar estudando <método>"
                "Você pode começar estudando escalas para desenvolver uma base sólida no seu instrumento. As escalas ajudam a melhorar a técnica, a familiaridade com as notas e a capacidade de improvisação em diferentes tonalidades.",
                "Começar com acordes é uma excelente maneira de aprender a estrutura das músicas e a tocar progressões básicas. A compreensão dos acordes permitirá que você acompanhe canções e crie suas próprias composições.",
                "Estudar partituras é fundamental para a leitura e interpretação musical. Este método proporciona uma base sólida na teoria musical e permite que você toque uma ampla gama de peças em diferentes estilos.",
                "A improvisação é um método essencial para explorar a criatividade musical. Ao praticar improvisação, você aprende a criar melodias espontâneas e a se adaptar rapidamente a diferentes contextos musicais.",
                "Técnicas básicas são o alicerce para qualquer instrumento. Começar com fundamentos sólidos garante que você desenvolva habilidades essenciais como postura, dedilhado e controle de dinâmica desde o início do aprendizado."
        };


        for (int i = 0; i < documents.length; i++) {
            String fileName = "doc" + (i + 1) + ".txt";
            File file = new File(DIRECTORY + fileName);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(documents[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Documentos gerados com sucesso!");
    }
}
