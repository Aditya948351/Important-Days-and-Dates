package com.importantdays.presentation.history

data class HistoryEvent(
    val title: String,
    val era: String,
    val date: String,
    val shortDetail: String,
    val fullDetail: String
)

data class TimelineHistoryEvent(
    val year: Int,
    val dateDisplay: String,
    val indianEventTitle: String,
    val indianEventDesc: String,
    val globalContextTitle: String,
    val globalContextDesc: String
)

object HistoryData {
    val eras = listOf(
        "Ancient Era",
        "Medieval Era",
        "Early Modern Era",
        "Modern Era",
        "Modern India"
    )

    val events = listOf(
        HistoryEvent(
            title = "Chandragupta Maurya",
            era = "Ancient Era",
            date = "c. 321-297 BCE",
            shortDetail = "Founded the Mauryan dynasty and unified much of the Indian subcontinent.",
            fullDetail = "Chandragupta Maurya is important because he founded the Mauryan dynasty and unified much of the Indian subcontinent under a centralized administration. He was mentored by Kautilya (Chanakya), overthrew the Nandas, expanded into Punjab after Alexander’s death, and later defeated Seleucus I, which made the Mauryan state a major imperial power.\\n\\n• c. 325 BCE: Chandragupta overthrew the Nanda dynasty and ascended the throne of Magadha, laying the foundation of the Mauryan dynasty.\\n• 323–322 BCE: After Alexander the Great died in 323 BCE, Chandragupta captured the Punjab region about 322 BCE, expanding his political base in northwestern India.\\n• 305 BCE: He defeated Seleucus I Nicator, a major successor of Alexander, helping secure the empire’s northwestern frontier.\\n• c. 321–297 BCE: His reign is usually dated from about 321 BCE to about 297 BCE, during which he built one of the largest empires in early Indian history."
        ),
        HistoryEvent(
            title = "Mahmud of Ghazni",
            era = "Medieval Era",
            date = "997–1030 CE",
            shortDetail = "Known for his repeated expeditions and raids into the Indian subcontinent.",
            fullDetail = "Mahmud of Ghazni is usually studied as a raider rather than as a ruler who created a stable Indian empire. His expeditions are remembered for repeated raids, plunder, and attacks on wealthy centers and temples, which had a significant political and economic impact on northwestern India.\\n\\n• 997–1030 CE: Mahmud ruled Ghazni during this period and carried out repeated campaigns into the Indian subcontinent.\\n• 1009/1020 CE: Ghūr was conquered by Mahmud of Ghazna, showing his military reach in the region."
        ),
        HistoryEvent(
            title = "Prithviraj Chauhan",
            era = "Medieval Era",
            date = "c. 1166 - 1192 CE",
            shortDetail = "One of the strongest Rajput rulers, fought in the famous Battles of Tarain.",
            fullDetail = "Prithviraj Chauhan became one of the strongest Rajput rulers of his time and fought Muhammad Ghori in the famous Battles of Tarain. He won in 1191, but Ghori changed tactics in 1192, used mounted archers and cavalry effectively, defeated the Chauhan forces, and opened the way for later Muslim political dominance in northern India.\\n\\n• c. 1166: Prithviraja III, popularly known as Prithviraj Chauhan, was born.\\n• c. 1177: He ascended the throne and inherited a kingdom stretching from Thanesar to Mewar.\\n• 1191: First battle of Tarain – Prithviraj’s forces defeated Muhammad Ghori.\\n• 1192: Second battle of Tarain – Muhammad Ghori returned with a stronger army and Prithviraj was defeated."
        ),
        HistoryEvent(
            title = "Chhatrapati Shivaji Maharaj",
            era = "Early Modern Era",
            date = "1630 - 1680 CE",
            shortDetail = "Built the independent Maratha state and challenged powerful regional empires.",
            fullDetail = "Shivaji Maharaj built the Maratha state, challenged powerful enemies (like the Mughals and Deccan sultanates), and became one of the most important figures in early modern Indian history. He used forts, speed, surprise, and mobile warfare as central tactics to his success.\\n\\n• 19 February 1630: Birth at Shivneri Fort.\\n• 1646 CE: Capture of Torna Fort, one of his earliest steps toward independent power.\\n• 10 November 1659: Afzal Khan episode, defeating the Bijapuri army at Pratapgad.\\n• 1664 CE: Surat raid, increasing his reputation and showing ability to strike deep into Mughal territory.\\n• 1666 CE: Agra visit and famous escape, strengthening his legend.\\n• 6 June 1674: Formal coronation as Chhatrapati at Raigad, marking the formal Maratha empire.\\n• 3 April 1680: Death at Raigad."
        ),
        HistoryEvent(
            title = "The National Emergency",
            era = "Modern India",
            date = "1975 - 1977",
            shortDetail = "A 21-month period where emergency powers were applied across India.",
            fullDetail = "The Emergency is a major constitutional and political episode in modern Indian history. It is remembered for censorship of the press, arrests of opposition leaders, limits on civil liberties, forced sterilization drives, and later constitutional change.\\n\\n• 25 June 1975: President Fakhruddin Ali Ahmed declared a state of emergency across India on the advice of Prime Minister Indira Gandhi.\\n• June 1975–March 1977: A 21-month period during which emergency powers were applied across the country.\\n• January 1977: Indira Gandhi called for a general election and released several imprisoned political figures.\\n• 21 March 1977: The Emergency was officially lifted after the electoral defeat of Gandhi’s government."
        ),
        HistoryEvent(
            title = "Article 370 Abrogation",
            era = "Modern India",
            date = "5 August 2019",
            shortDetail = "Revocation of the special status of Jammu and Kashmir.",
            fullDetail = "The Government of India revoked the special status, or limited autonomy, granted under Article 370 of the Indian Constitution to Jammu and Kashmir—a region administered by India as a state. \\n\\n• The Parliament of India passed the Jammu and Kashmir Reorganisation Act, which contained provisions that dissolved the state and reorganized it into two union territories: Jammu and Kashmir in the west and Ladakh in the east.\\n• This move marked a significant shift in India's policy toward the region, aiming for full integration of the territory into the Union of India."
        )
    )

    val ancientTimelineEvents = listOf(
        TimelineHistoryEvent(
            year = -2500,
            dateDisplay = "c. 2500 BCE",
            indianEventTitle = "Indus Valley Civilization",
            indianEventDesc = "Mature phase of Harappan civilization begins. Urban planning, drainage systems, and trade flourish.",
            globalContextTitle = "Old Kingdom of Egypt",
            globalContextDesc = "Construction of the Great Pyramid of Giza under Pharaoh Khufu."
        ),
        TimelineHistoryEvent(
            year = -2000,
            dateDisplay = "c. 2000 BCE",
            indianEventTitle = "Decline of Indus Valley",
            indianEventDesc = "Climate change and shifting river courses lead to the decline of major Harappan cities.",
            globalContextTitle = "Minoan Civilization",
            globalContextDesc = "Flourishing of the Minoan civilization on the island of Crete, early European civilization."
        ),
        TimelineHistoryEvent(
            year = -1500,
            dateDisplay = "c. 1500 BCE",
            indianEventTitle = "Early Vedic Period",
            indianEventDesc = "Composition of the Rigveda begins. Indo-Aryan migration into the Punjab region.",
            globalContextTitle = "Shang Dynasty",
            globalContextDesc = "The first historically confirmed dynasty in China emerges, known for bronze work."
        ),
        TimelineHistoryEvent(
            year = -1000,
            dateDisplay = "c. 1000 BCE",
            indianEventTitle = "Later Vedic Period",
            indianEventDesc = "Use of iron begins in India; Aryans expand into the Gangetic plains. Caste system roots solidify.",
            globalContextTitle = "Kingdom of Israel",
            globalContextDesc = "King David rules the United Monarchy of Israel and Judah."
        ),
        TimelineHistoryEvent(
            year = -563,
            dateDisplay = "c. 563 BCE",
            indianEventTitle = "Birth of Gautama Buddha",
            indianEventDesc = "Siddhartha Gautama is born in Lumbini, leading to the foundation of Buddhism.",
            globalContextTitle = "Persian Empire",
            globalContextDesc = "Cyrus the Great begins expansion of the Achaemenid Empire."
        ),
        TimelineHistoryEvent(
            year = -540,
            dateDisplay = "c. 540 BCE",
            indianEventTitle = "Birth of Mahavira",
            indianEventDesc = "Vardhamana Mahavira is born, leading to the spread and formalization of Jainism.",
            globalContextTitle = "Roman Republic",
            globalContextDesc = "The Roman Republic is established after overthrowing the Roman Kingdom (c. 509 BCE)."
        ),
        TimelineHistoryEvent(
            year = -326,
            dateDisplay = "326 BCE",
            indianEventTitle = "Battle of the Hydaspes",
            indianEventDesc = "Alexander the Great defeats King Porus in Punjab, but mutiny forces him to retreat.",
            globalContextTitle = "Hellenistic Age",
            globalContextDesc = "Alexander's campaigns spread Greek culture across the Middle East and Asia."
        ),
        TimelineHistoryEvent(
            year = -321,
            dateDisplay = "321 BCE",
            indianEventTitle = "Mauryan Empire Founded",
            indianEventDesc = "Chandragupta Maurya overthrows the Nanda Dynasty and establishes the Mauryan Empire.",
            globalContextTitle = "Wars of the Diadochi",
            globalContextDesc = "Alexander's generals fight for control over his fragmented empire."
        ),
        TimelineHistoryEvent(
            year = -261,
            dateDisplay = "261 BCE",
            indianEventTitle = "Kalinga War",
            indianEventDesc = "Ashoka the Great conquers Kalinga. The massive bloodshed leads to his embrace of Buddhism and Ahimsa.",
            globalContextTitle = "First Punic War",
            globalContextDesc = "Rome and Carthage begin their massive conflict over dominance in the Mediterranean."
        ),
        TimelineHistoryEvent(
            year = 320,
            dateDisplay = "320 CE",
            indianEventTitle = "Gupta Empire Founded",
            indianEventDesc = "Sri Gupta founds the dynasty. Often called the 'Golden Age' of India for advancements in arts, science, and math.",
            globalContextTitle = "Roman Empire Split",
            globalContextDesc = "The Roman Empire begins its decline, moving its capital to Constantinople."
        ),
        TimelineHistoryEvent(
            year = 499,
            dateDisplay = "499 CE",
            indianEventTitle = "Aryabhatiya Authored",
            indianEventDesc = "Aryabhata writes his magnum opus, introducing concepts like zero and planetary rotation.",
            globalContextTitle = "Fall of Western Roman Empire",
            globalContextDesc = "Romulus Augustulus is deposed (476 CE), plunging Western Europe into the Dark Ages."
        )
    )

    val medievalTimelineEvents = listOf(
        TimelineHistoryEvent(
            year = 712,
            dateDisplay = "712 CE",
            indianEventTitle = "Umayyad Campaign in India",
            indianEventDesc = "Muhammad bin Qasim conquers Sindh, marking the beginning of Muslim rule in parts of the subcontinent.",
            globalContextTitle = "Umayyad Caliphate Expansion",
            globalContextDesc = "The Umayyad Caliphate reaches its greatest extent, spanning from Spain to India."
        ),
        TimelineHistoryEvent(
            year = 800,
            dateDisplay = "c. 800 CE",
            indianEventTitle = "Tripartite Struggle",
            indianEventDesc = "Gurjara-Pratiharas, Rashtrakutas, and Palas fight for control of Kannauj in North India.",
            globalContextTitle = "Carolingian Empire",
            globalContextDesc = "Charlemagne is crowned Emperor of the Romans by Pope Leo III."
        ),
        TimelineHistoryEvent(
            year = 1000,
            dateDisplay = "1000-1027 CE",
            indianEventTitle = "Mahmud of Ghazni Campaigns",
            indianEventDesc = "Mahmud conducts numerous raids deep into the Indian subcontinent, targeting wealthy temples.",
            globalContextTitle = "Viking Explorations",
            globalContextDesc = "Leif Erikson reaches North America."
        ),
        TimelineHistoryEvent(
            year = 1014,
            dateDisplay = "1014 CE",
            indianEventTitle = "Chola Expansion",
            indianEventDesc = "Rajendra Chola I begins massive naval campaigns, extending Chola influence to Southeast Asia.",
            globalContextTitle = "Song Dynasty in China",
            globalContextDesc = "Flourishing of Chinese arts, literature, and the first use of paper money."
        ),
        TimelineHistoryEvent(
            year = 1192,
            dateDisplay = "1192 CE",
            indianEventTitle = "Second Battle of Tarain",
            indianEventDesc = "Muhammad Ghori defeats Prithviraj Chauhan, laying the foundation for the Delhi Sultanate.",
            globalContextTitle = "Third Crusade",
            globalContextDesc = "Richard the Lionheart and Saladin negotiate a truce in the Holy Land."
        ),
        TimelineHistoryEvent(
            year = 1206,
            dateDisplay = "1206 CE",
            indianEventTitle = "Delhi Sultanate Founded",
            indianEventDesc = "Qutb ud-Din Aibak establishes the Mamluk (Slave) Dynasty.",
            globalContextTitle = "Mongol Empire Established",
            globalContextDesc = "Genghis Khan unites the Mongol tribes."
        ),
        TimelineHistoryEvent(
            year = 1336,
            dateDisplay = "1336 CE",
            indianEventTitle = "Vijayanagara Empire Founded",
            indianEventDesc = "Harihara I and Bukka Raya I establish the empire in South India, a bastion of Hindu culture.",
            globalContextTitle = "Hundred Years' War Begins",
            globalContextDesc = "Conflict erupts between the kingdoms of England and France."
        ),
        TimelineHistoryEvent(
            year = 1440,
            dateDisplay = "1440 CE",
            indianEventTitle = "Bhakti Movement Peaks",
            indianEventDesc = "Mystics like Kabir and later Guru Nanak preach equality and devotion, transforming Indian society.",
            globalContextTitle = "Invention of Printing Press",
            globalContextDesc = "Johannes Gutenberg invents the movable-type printing press in Europe."
        )
    )

    val earlyModernTimelineEvents = listOf(
        TimelineHistoryEvent(
            year = 1498,
            dateDisplay = "1498 CE",
            indianEventTitle = "Vasco da Gama arrives",
            indianEventDesc = "Portuguese explorer reaches Calicut, opening a direct sea route from Europe to India.",
            globalContextTitle = "Age of Discovery",
            globalContextDesc = "Christopher Columbus makes his voyages to the Americas."
        ),
        TimelineHistoryEvent(
            year = 1526,
            dateDisplay = "1526 CE",
            indianEventTitle = "First Battle of Panipat",
            indianEventDesc = "Babur defeats Ibrahim Lodi and establishes the Mughal Empire.",
            globalContextTitle = "Ottoman Empire Peaks",
            globalContextDesc = "Suleiman the Magnificent expands the Ottoman Empire."
        ),
        TimelineHistoryEvent(
            year = 1556,
            dateDisplay = "1556 CE",
            indianEventTitle = "Akbar the Great",
            indianEventDesc = "Akbar ascends the throne, expanding the Mughal Empire and promoting religious tolerance.",
            globalContextTitle = "Elizabethan Era",
            globalContextDesc = "Elizabeth I begins her reign in England, a golden age of literature."
        ),
        TimelineHistoryEvent(
            year = 1600,
            dateDisplay = "1600 CE",
            indianEventTitle = "East India Company",
            indianEventDesc = "The British East India Company is granted a royal charter to trade in the East Indies.",
            globalContextTitle = "Scientific Revolution",
            globalContextDesc = "Galileo and Kepler lay foundations for modern science."
        ),
        TimelineHistoryEvent(
            year = 1632,
            dateDisplay = "1632 CE",
            indianEventTitle = "Taj Mahal Construction Begins",
            indianEventDesc = "Shah Jahan commissions the Taj Mahal in memory of his wife, Mumtaz Mahal.",
            globalContextTitle = "Thirty Years' War",
            globalContextDesc = "A devastating religious and political conflict ravages Central Europe."
        ),
        TimelineHistoryEvent(
            year = 1674,
            dateDisplay = "1674 CE",
            indianEventTitle = "Maratha Empire Founded",
            indianEventDesc = "Chhatrapati Shivaji Maharaj is crowned, establishing an independent Maratha kingdom.",
            globalContextTitle = "Golden Age of Piracy",
            globalContextDesc = "Rise of maritime piracy in the Caribbean."
        ),
        TimelineHistoryEvent(
            year = 1707,
            dateDisplay = "1707 CE",
            indianEventTitle = "Death of Aurangzeb",
            indianEventDesc = "Marks the beginning of the decline of the Mughal Empire and rise of regional powers.",
            globalContextTitle = "Act of Union",
            globalContextDesc = "England and Scotland unite to form the Kingdom of Great Britain."
        )
    )

    val modernTimelineEvents = listOf(
        TimelineHistoryEvent(
            year = 1757,
            dateDisplay = "1757",
            indianEventTitle = "Battle of Plassey",
            indianEventDesc = "Robert Clive defeats the Nawab of Bengal, marking the start of British political rule in India.",
            globalContextTitle = "Seven Years' War",
            globalContextDesc = "First truly global conflict spanning Europe, Americas, and Asia."
        ),
        TimelineHistoryEvent(
            year = 1857,
            dateDisplay = "1857",
            indianEventTitle = "Revolt of 1857",
            indianEventDesc = "First War of Independence leads to the end of Company rule and the start of the British Raj.",
            globalContextTitle = "Industrial Revolution",
            globalContextDesc = "Rapid industrialization sweeps across Europe and the USA."
        ),
        TimelineHistoryEvent(
            year = 1885,
            dateDisplay = "1885",
            indianEventTitle = "INC Founded",
            indianEventDesc = "The Indian National Congress is formed, paving the way for the organized independence movement.",
            globalContextTitle = "Scramble for Africa",
            globalContextDesc = "European powers rapidly colonize the African continent."
        ),
        TimelineHistoryEvent(
            year = 1905,
            dateDisplay = "1905",
            indianEventTitle = "Partition of Bengal",
            indianEventDesc = "Lord Curzon partitions Bengal, triggering the Swadeshi movement and massive boycotts.",
            globalContextTitle = "Russo-Japanese War Ends",
            globalContextDesc = "Japan defeats Russia, becoming the first Asian power to defeat a European power in modern times."
        ),
        TimelineHistoryEvent(
            year = 1919,
            dateDisplay = "1919",
            indianEventTitle = "Jallianwala Bagh Massacre",
            indianEventDesc = "British troops fire on peaceful protestors in Amritsar. Rowlatt Act is passed.",
            globalContextTitle = "Treaty of Versailles",
            globalContextDesc = "Peace treaty signed, officially ending WWI and redrawing the map of Europe."
        ),
        TimelineHistoryEvent(
            year = 1930,
            dateDisplay = "1930",
            indianEventTitle = "Dandi March",
            indianEventDesc = "Gandhi leads the Salt March, initiating the Civil Disobedience Movement.",
            globalContextTitle = "Great Depression",
            globalContextDesc = "The world economy suffers massive collapse following the 1929 stock market crash."
        ),
        TimelineHistoryEvent(
            year = 1942,
            dateDisplay = "1942",
            indianEventTitle = "Quit India Movement",
            indianEventDesc = "Gandhi launches the 'Do or Die' campaign, demanding an end to British rule.",
            globalContextTitle = "Battle of Stalingrad",
            globalContextDesc = "Major turning point in WWII as Soviet forces halt the German advance."
        ),
        TimelineHistoryEvent(
            year = 1947,
            dateDisplay = "1947",
            indianEventTitle = "Independence & Partition",
            indianEventDesc = "India gains independence from British rule. The subcontinent is partitioned into India and Pakistan.",
            globalContextTitle = "Cold War Begins",
            globalContextDesc = "Ideological conflict begins between the USA and the Soviet Union."
        ),
        TimelineHistoryEvent(
            year = 1950,
            dateDisplay = "1950",
            indianEventTitle = "Republic of India",
            indianEventDesc = "The Constitution of India comes into effect, making it a sovereign democratic republic.",
            globalContextTitle = "Korean War",
            globalContextDesc = "Conflict breaks out between North and South Korea."
        ),
        TimelineHistoryEvent(
            year = 1971,
            dateDisplay = "1971",
            indianEventTitle = "Indo-Pak War & Bangladesh",
            indianEventDesc = "India defeats Pakistan, leading to the creation of the independent nation of Bangladesh.",
            globalContextTitle = "End of Bretton Woods",
            globalContextDesc = "US President Nixon ends the gold standard for the US Dollar."
        ),
        TimelineHistoryEvent(
            year = 1975,
            dateDisplay = "1975",
            indianEventTitle = "The Emergency",
            indianEventDesc = "Prime Minister Indira Gandhi declares a 21-month state of emergency.",
            globalContextTitle = "Vietnam War Ends",
            globalContextDesc = "Fall of Saigon marks the end of the Vietnam War."
        ),
        TimelineHistoryEvent(
            year = 1991,
            dateDisplay = "1991",
            indianEventTitle = "Economic Liberalisation",
            indianEventDesc = "India opens its economy, launching sweeping reforms that accelerate economic growth.",
            globalContextTitle = "Fall of the Soviet Union",
            globalContextDesc = "The USSR dissolves, ending the Cold War."
        ),
        TimelineHistoryEvent(
            year = 1998,
            dateDisplay = "1998",
            indianEventTitle = "Pokhran-II Tests",
            indianEventDesc = "India conducts five underground nuclear weapon tests, becoming a declared nuclear state.",
            globalContextTitle = "Good Friday Agreement",
            globalContextDesc = "A major political development in the Northern Ireland peace process."
        )
    )

    val modernEraTimelineEvents = listOf(
        TimelineHistoryEvent(
            year = 1789,
            dateDisplay = "1789 CE",
            indianEventTitle = "Third Anglo-Mysore War Begins",
            indianEventDesc = "Conflict between the British East India Company and the Kingdom of Mysore under Tipu Sultan.",
            globalContextTitle = "French Revolution",
            globalContextDesc = "The Storming of the Bastille marks the beginning of the French Revolution, reshaping Europe."
        ),
        TimelineHistoryEvent(
            year = 1815,
            dateDisplay = "1815 CE",
            indianEventTitle = "British Consolidation",
            indianEventDesc = "End of the Anglo-Nepalese war and shifting of British focus to consolidating Indian territories.",
            globalContextTitle = "Battle of Waterloo",
            globalContextDesc = "Napoleon Bonaparte is finally defeated, ending the Napoleonic Wars in Europe."
        ),
        TimelineHistoryEvent(
            year = 1853,
            dateDisplay = "1853 CE",
            indianEventTitle = "First Passenger Train",
            indianEventDesc = "First train runs from Bombay to Thane, ushering in the railway age in India.",
            globalContextTitle = "Opening of Japan",
            globalContextDesc = "Commodore Matthew Perry arrives in Japan, ending its isolationist policy."
        ),
        TimelineHistoryEvent(
            year = 1861,
            dateDisplay = "1861 CE",
            indianEventTitle = "Indian Councils Act",
            indianEventDesc = "The Act transforms the Viceroy's executive council into a cabinet on the portfolio system.",
            globalContextTitle = "American Civil War",
            globalContextDesc = "The American Civil War begins between the Union and the Confederacy."
        ),
        TimelineHistoryEvent(
            year = 1869,
            dateDisplay = "1869 CE",
            indianEventTitle = "Birth of Mahatma Gandhi",
            indianEventDesc = "Mohandas Karamchand Gandhi is born in Porbandar, Gujarat.",
            globalContextTitle = "Suez Canal Opens",
            globalContextDesc = "The canal opens in Egypt, drastically reducing travel time between Europe and Asia."
        ),
        TimelineHistoryEvent(
            year = 1914,
            dateDisplay = "1914 CE",
            indianEventTitle = "India in WWI",
            indianEventDesc = "Over one million Indian troops serve overseas in the British Indian Army.",
            globalContextTitle = "World War I Begins",
            globalContextDesc = "The assassination of Archduke Franz Ferdinand triggers a global conflict."
        ),
        TimelineHistoryEvent(
            year = 1917,
            dateDisplay = "1917 CE",
            indianEventTitle = "Champaran Satyagraha",
            indianEventDesc = "Gandhi's first major Satyagraha movement in India, supporting indigo farmers.",
            globalContextTitle = "Russian Revolution",
            globalContextDesc = "The Bolsheviks overthrow the Tsarist autocracy, leading to the creation of the USSR."
        ),
        TimelineHistoryEvent(
            year = 1939,
            dateDisplay = "1939 CE",
            indianEventTitle = "India in WWII",
            indianEventDesc = "The Viceroy declares India's entry into the war without consulting Indian leaders.",
            globalContextTitle = "World War II Begins",
            globalContextDesc = "Nazi Germany invades Poland, igniting the deadliest conflict in human history."
        ),
        TimelineHistoryEvent(
            year = 1945,
            dateDisplay = "1945 CE",
            indianEventTitle = "INA Trials",
            indianEventDesc = "Trials of Indian National Army officers spark massive nationalist sentiment across India.",
            globalContextTitle = "Atomic Bombings",
            globalContextDesc = "USA drops atomic bombs on Hiroshima and Nagasaki, ending WWII. UN is formed."
        ),
        TimelineHistoryEvent(
            year = 1969,
            dateDisplay = "1969 CE",
            indianEventTitle = "ISRO Founded",
            indianEventDesc = "The Indian Space Research Organisation is formed to harness space technology.",
            globalContextTitle = "Apollo 11 Moon Landing",
            globalContextDesc = "Neil Armstrong and Buzz Aldrin become the first humans to walk on the Moon."
        )
    )

    fun getEventsForEra(era: String): List<HistoryEvent> {
        return events.filter { it.era == era }
    }

    fun getEventByTitle(title: String): HistoryEvent? {
        return events.find { it.title == title }
    }
}
