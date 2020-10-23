import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

public class CreatePerson {


    public CreatePerson() {

    }

    public static Person createKoisumiSan() {
        try {
            Address addr = new Address("123", "Honkai Impact 1", "Tokyo", 123453);
            Person koisumiSan = new Person("Koisumi 小泉さん", "Unknown", "", "Female", addr, "\"As a recent transfer student, Koizumi-san has always been shrouded in mystery among her classmates. Nothing much is known about Koizumi-san, and even her first name is never mentioned. Nevertheless, her beauty has made her very popular among boys. \\n\\nIt was her classmate Yuu who discovered Koizumi-san's fascination with ramen. Koizumi-san would go to any length to discover new ramen dishes around Japan, even if it means climbing a mountain or failing a school test. What is perhaps most unusual about Koizumi-san is that she has no fear in visiting ramen shops by herself, which are always populated by male customers. The fear and embarrassment that is usually felt by girls who visit Japanese ramen shops alone simply does not register with Koizumi-san in the slightest - eating ramen is the ultimate source of happiness for her.\"");
            Person child = new Person("Ramen", "Unknown", "", "Female", addr, "Child of Koisumi-San is Ramen");
            Person spouse = new Person("Osawa", "Yuu", "Unknown", "Female", addr, "Yuu is a 16 year-old, first-year high school student. She is in the same class as Koizumi-san, and is best friends with Misa and Jun, who are both first-years as well. Yuu lives in an apartment with her parents and older brother, Shuu. She also has an older cousin, Ayane, who very much resembles Yuu in appearance and personality.\n For as long as her friends can remember, Yuu has always had a penchant for girls who looked cute. When she met Koizumi-san, Yuu was fully determined to try to become friends with her. This would involve wanting to tag along with Koizumi-san on her ramen trips, even when uninvited. Her persistence rubs off on Koizumi-san as being obsessive, if not stalker-like.\nShe has a knack for making homemade Japanese meals for her friends and family.");
            koisumiSan.setSpouse(spouse);
            koisumiSan.setChildren(child);
            return koisumiSan;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

    }

    public static void setPersonParents(Person person, Person parent) throws Exception {
        person.setParents(parent);
        parent.setChildren(person);
    }


    public static Person createKirito() {
        try {
            Address addr = new Address("123", "Sword Art Online", "Tokyo", 123453);
            Address addr2 = new Address("123", "Sword Art Online 2", "Tokyo", 123453);
            Address addr3 = new Address("123", "Accel World", "Tokyo", 123453);
            Person kirito = new Person("Kazuto 和人", "Kirigaya 桐ヶ谷", "Kirigaya 桐ヶ谷", "Male", addr, "Kirigaya Kazuto (桐ヶ谷 和人, Kirigaya Kazuto?), born as Narusaka Kazuto (鳴坂 和人なるさか かずと, Narusaka Kazuto?)[1], and known as Kirito (キリト, Kirito?) in «Sword Art Online» (SAO), «ALfheim Online» (ALO), «Gun Gale Online» (GGO), and «Project Alicization», is the protagonist of the main Sword Art Online series.");
            Person midori = new Person("Midori 翠", "Aoi", "Kirigaya 桐ヶ谷", "female", addr, "Kirigaya Midori (桐ヶ谷翠, Kirigaya Midori?) is the mother of Suguha, as well as the aunt and adoptive mother of Kazuto. She is an extremely busy woman and leaves the two kids to take care of themselves, only coming back home late in the night and leaving early in the morning, though she still shows love and care for them.");
            Person minetaka = new Person("Minetaka", "Kirigaya 桐ヶ谷", "Kirigaya 桐ヶ谷", "male", addr, "Unknown");
            Person asuna = new Person("Asuna 明日奈", "Yuuki 結城", "Kirigaya 桐ヶ谷", "female", addr2, "Yuuki Asuna (結城 明日奈, Yūki Asuna?), known as Asuna (アスナ, Asuna?) in «Sword Art Online» (SAO), «ALfheim Online» (ALO), as well as «Project Alicization», where she had temporarily used the Stacia (ステイシア, Suteishia?) account, is the main heroine of the Sword Art Online series[7], the deuteragonist of the Aincrad Arc, as well as the protagonist of the Mother's Rosario side story. She is the daughter of the former CEO of RECT Inc.. Asuna was one of the 10,000 players trapped in «Sword Art Online», where she was the sub-leader of the «Knights of the Blood» (KoB) guild.[3] Her skills with the rapier had her earned her the nickname «The Flash» (閃光, Senkō?).");
            Person yui = new Person("Yui ユイ", "", "", "female", addr, "Yui (ユイ, Yui?) is an Artificial Intelligence (AI), found by Kirito and Asuna around the forests of the 22nd Floor of the floating castle Aincrad. Her official name is «Yui-MHCP001» (Mental Health Counseling Program 001).");
            Person blackLotus = new Person("Black Lotus", "", "", "female", addr, "Her \"Burst Brain\" character \"Black Lotus\" (ブラック・ロータス, Burakku Rōtasu) was once known as the Black King (黒王, Kuroō) but after she killed the Red King, she went into hiding.\nBlack Lotus was known as a fearsome model before and after what happened to the Red King. However, she rarely reveals her Black Lotus form. The form of Black Lotus is more technical and robotic than her virtual character. It seems that she can only soar in a short length in the air instead of flying. The character model's color is black and purple. Black Lotus is unique since her limbs are blades instead.");
            Person kuroHime = new Person("Kurohime", "Princess", "Arita", "female", addr3, "Kuroyukihime (黒雪姫, Kuroyukihime) is one of the main characters in the Accel World series. She is the Black King (黒王, Kuroō), Black Lotus (ブラック ロータス, Burakku Rōtasu), and the leader of the Black Legion, Nega Nebulus. She is the one who gave the Brain Burst program of Haruyuki and now works together with him to reach Level 10 and meet the creator the Accelerated World by defeating the other Pure-Colored Kings. \nHolding true to being \"the most beautiful girl in school,\" Kuroyukihime is a very attractive and slender young girl with long black hair that has two strands of hair extended from the top of her brow, resembling the antenna of a butterfly and large hazel eyes. When Kuroyukihime was hospitalized, she had her hair in a ponytail. Her wardrobe is usually her school uniform consisting of black tights, a dark green skirt with a brown trim, a navy blue blazer, and a light blue bow tie. As she moves to her final year at school, her uniform alters to a red bow tie with all else being the same. It is extremely rare to see Kuroyukihime in casual clothing. It is somehow known that she prefers to wear black colored clothes.");
            Person haruYuki = new Person("Haruyuki", "Arita", "Arita", "Male", addr3, "Haruyuki Arita (有田 春雪, Arita Haruyuki) is the main protagonist of the Accel World series. He becomes the Burst Linker Silver Crow (シルバー・クロウ, Shirubā Kurō) after he receives the Brain Burst program from Kuroyukihime. He now works together with her to reach Level 10 and meet the creator of the Accelerated World by defeating The Six Kings of Pure Color.\n Haru is rather timid at first due to Araya bullying him. However, after meeting Kuroyukihime and receiving Brain Burst, he can stand up for himself and help Kuroyukihime in achieving her dreams. Later on in the course of the plot, he begins to admit that beyond helping her with her goal of reaching level 10 and knowing the purpose of Brain Burst, little by little he discovers that his dream is in fact, to always be by her side for Kuroyukihime is the person he loves the most even though he does not have the courage to tell her, despite knowing that she also loves him unconditionally. He is also very determined to keep his friendship with Taku and Chiyu after the many problems between them and because of that determination, he strives for the best. Haruyuki is also rather indecisive and often doubts himself. However, as Silver Crow, he is more confident and rather crafty to the point that it was comparable to Yellow Radio");

            kirito.setParents(minetaka);
            kirito.setParents(midori);
            kirito.setSpouse(asuna);
            kirito.setChildren(yui);
            kirito.setChildren(kuroHime);
            kuroHime.setChildren(blackLotus);
            haruYuki.setSpouse(kuroHime);

            return kirito;
        } catch (Exception e) {
            System.out.println(e);
            return new Person();
        }
    }
}
