import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.effect.PerspectiveTransform;

import java.util.ArrayList;

/**
 * My test class that creates some example family tree.
 * To use this change the constructor to initialise rootPerson from here in the RootObservable.
 * Example: rootPerson = CreatePerson.createHarryPotter;
 */
public class CreatePerson {

    public static Person createHarryPotter() {
        try {
            Address harryHousehold = new Address("1A", "Harry Street 1", "Gryffindor", 4340);
            Address weasleyHousehold = new Address("20A", "Weasley Street 1", "Gryffindor", 4570);
            Person harryPotter = new Person("Harry", "Potter", "Potter", "male", harryHousehold, "Harry James Potter is a fictional character and the titular protagonist in J. K. Rowling's series of eponymous novels. The majority of the books' plot covers seven years in the life of the orphan Harry, who, on his eleventh birthday, learns he is a wizard. Thus, he attends Hogwarts School of Witchcraft and Wizardry to practice magic under the guidance of the kindly headmaster Albus Dumbledore and other school professors along with his best friends Ron Weasley and Hermione Granger. Harry also discovers that he is already famous throughout the novel's magical community, and that his fate is tied with that of Lord Voldemort – the internationally feared Dark Wizard and murderer of his parents, Lily and James. The film and book series revolve around Harry's struggle to adapt to the wizarding world and defeat Voldemort.");
            Person ginnyWeasly = new Person("Ginny", "Weasley", "Potter", "Female", harryHousehold, "Ginevra Molly Weasley is a fictional character in J. K. Rowling's Harry Potter novel series. Ginny is introduced in the first book Harry Potter and the Philosopher's Stone, as the youngest sibling and only girl in the Weasley family. She becomes Harry's main love interest and eventually marries him at the conclusion of the series.");
            Person arthurWeasley = new Person("Arthur", "Weasley", "Weasley", "male", weasleyHousehold, "Arthur Weasley is the patriarch of the Weasleys, a family of wizards who are considered \"blood traitors\" by Death Eaters for their interest in the Muggle world. He is married to Molly Weasley, with whom he has seven children, including Ron, Harry's best friend. During his time at Hogwarts, Arthur belonged to the house of Gryffindor. Arthur is described as being tall and thin, and as having a receding hairline and horn-rimmed glasses. An affable, light-hearted man, he tends not to be the authority figure in the family; his wife Molly handles that area. Arthur works for the Ministry of Magic, initially in the Misuse of Muggle Artefacts Office. He is obsessed with learning about Muggle customs and inventions and owns a large collection of mostly Muggle used items. His department lacks funding, and his salary is only just able to provide for a vast family, leaving his family finances precarious.");
            Person mollyWeasley = new Person("Molly", "Prewett", "Weasley", "female", weasleyHousehold, "Molly Weasley (née Prewett)[36] is the wife of Arthur Weasley and the matriarch of the Weasley family. She is the mother of seven children, including Ron Weasley, who becomes Harry Potter's best friend. Molly is born into the pure-blood Prewett family, being the sister of Gideon and Fabian Prewett. The character is first introduced in Harry Potter and the Philosopher's Stone, when she kindly tells Harry how to cross the barrier through to Platform Nine and Three-Quarters. In Harry Potter and the Chamber of Secrets, she is furious with Fred, George, and Ron after she discovers that they flew their parents' enchanted car to rescue Harry from his aunt and uncle who had imprisoned him in his room. At the beginning of the school year, Molly sends Ron a Howler, screaming at him in anger that he and Harry flew the family car again, this time to Hogwarts. In Harry Potter and the Prisoner of Azkaban, the Weasleys win the Daily Prophet draw and use the gold on a trip to Egypt to visit Bill. They return to Britain and stay at the Leaky Cauldron with Harry and Hermione. Harry overhears Mr and Mrs Weasley arguing one night about telling Harry the truth about the supposed connection between Sirius Black and Harry; Arthur feels Harry should know the truth but Molly, feeling the truth would terrify him, assures him Harry will be perfectly safe at Hogwarts with Dumbledore's protection, and orders Percy Weasley to keep an eye on Harry at the school.");
            Person jamesPotter = new Person("James", "Potter", "Potter", "male", harryHousehold, "Harry Potter's parents, who first met at Hogwarts. They were killed by Voldemort, who was attempting to kill the fifteen-month-old Harry.");
            Person lilyEvansPotter = new Person("Lily", "Evans", "Potter", "female", harryHousehold, "Harry Potter's parents, who first met at Hogwarts. They were killed by Voldemort, who was attempting to kill the fifteen-month-old Harry.");
            Person siriusPotter = new Person("Sirius", "Potter", "", "male", harryHousehold, "James Sirius Potter is the first-born child of Harry and Ginny, born two years before his brother Albus Severus. He was named after his paternal grandfather James Potter and his father's godfather Sirius Black, and he is described as being similar in character to both of his namesakes as well as his uncles, Fred and George Weasley, with a penchant for practical jokes and general misbehaviour. James is portrayed by Will Dunn in Deathly Hallows: Part 2.[HP7] James Sirius Potter was born on 16 November 2004.");
            Person albusPotter = new Person("Albus", "Potter", "", "male", harryHousehold, "Albus Severus Potter is Harry and Ginny's second-born child. Albus is said to look much like Harry and to be the only one of his siblings to have inherited Lily Potter's green eyes. He is named after Albus Dumbledore and Severus Snape, and would be eleven at the time of the epilogue. Albus is portrayed by Arthur Bowen in Harry Potter and the Deathly Hallows – Part 2.\nAlbus is one of the main characters in the two-part stage play Harry Potter and the Cursed Child. He has been played in the West End production by Sam Clemmett, Theo Ancient, Joe Idris-Roberts, and Dominic Short. Albus Severus Potter was born in 2006.");
            Person lilyLunaPotter = new Person("Lily", "Potter", "", "female", harryHousehold, "Lily Luna Potter is the daughter of Harry and Ginny, the youngest of their three children. Her name honours two women important to her parents—her paternal grandmother Lily Potter, and her parents' good friend Luna Lovegood. She is two years younger than her brother Albus Severus, making her around nine, and is close in age to her cousin Hugo. It is also stated that she has her mother's and possibly both of her own grandmothers' red hair. In the epilogue of Harry Potter and the Deathly Hallows, she accompanied her parents as they escorted James and Albus Potter to Platform 9¾ to the Hogwarts Express.");
            Person ronWeasly = new Person("Ron", "Weasley", "Weasley", "male", weasleyHousehold, "Ronald Bilius Weasley is a fictional character in J. K. Rowling's Harry Potter fantasy novel series. His first appearance was in the first book of the series, Harry Potter and the Philosopher's Stone, as the best friend of Harry Potter and Hermione Granger. He is a member of the Weasley family, a pure blood family that resides in \"The Burrow\" outside Ottery St. Catchpole. Along with Harry and Hermione, he is a member of Gryffindor house. Ron is present in most of the action throughout the series.");
            Person hermioneGranger = new Person("Hermione", "Granger", "Weasley", "Female", weasleyHousehold, "Hermione Jean Granger is a fictional character in J. K. Rowling's Harry Potter series. She first appears in Harry Potter and the Philosopher's Stone, as a new student on her way to Hogwarts. After Harry and Ron save her from a mountain troll in the girls' restroom, she becomes best friends with them and often uses her quick wit, deft recall, and encyclopaedic knowledge to lend aid in dire situations. Rowling has stated that Hermione resembles herself as a young girl, with her insecurity and fear of failure.");
            Person roseWeasley = new Person("Rose", "Weasley", "", "female", weasleyHousehold, "Rose Weasley is Hermione and Ron's daughter and older child. Rose is introduced in the Harry Potter and the Deathly Hallows epilogue in which she is leaving for her first year at Hogwarts, as is her cousin Albus Severus Potter. According to Ron she inherited her mother's brains and ambition.");
            Person hugoWeasley = new Person("Hugo", "Weasley", "", "male", weasleyHousehold, "Hugo Weasley is Hermione and Ron's son and younger child. He is close in age to Harry and Ginny's daughter and youngest child, Lily Luna Potter, and had not yet started at Hogwarts by the epilogue of Deathly Hallows.");
            Person malePrewett = new Person("Male", "Prewett", "Prewett", "male", weasleyHousehold, "The father of Molly prewett. not much known about him");
            harryPotter.setSpouse(ginnyWeasly);
            harryPotter.setParents(lilyEvansPotter);
            harryPotter.setParents(jamesPotter);
            harryPotter.setChildren(siriusPotter);
            harryPotter.setChildren(lilyLunaPotter);
            harryPotter.setChildren(albusPotter);
            mollyWeasley.setSpouse(arthurWeasley);
            mollyWeasley.setParents(malePrewett);
            ginnyWeasly.setParents(arthurWeasley);
            ginnyWeasly.setParents(mollyWeasley);
            ronWeasly.setParents(arthurWeasley);
            ronWeasly.setParents(mollyWeasley);
            ronWeasly.setSpouse(hermioneGranger);
            ronWeasly.setChildren(roseWeasley);
            ronWeasly.setChildren(hugoWeasley);
            return harryPotter;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

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
            e.printStackTrace();


            return new Person();
        }
    }
}
