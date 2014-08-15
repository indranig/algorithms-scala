package org.awong.fundamentals

object FundamentalsData extends org.awong.stdlib.ModuleData {
	def testTransactions: Seq[Transaction] = {
		Seq(
			Transaction("Turing", "6/17/1990", 655.08),
			Transaction("vonNeumann", "3/26/2002 ", 4121.85),
			Transaction("vonNeumann","3/26/2002",4121.85),
			Transaction("Dijkstra","8/22/2007",2678.40),
			Transaction("vonNeumann","1/11/1999",4409.74),
			Transaction("Dijkstra","11/18/1995",837.42),
			Transaction("Hoare","5/10/1993",3229.27),
			Transaction("vonNeumann ","2/12/1994",4732.35),
			Transaction("Hoare","8/18/1992",4381.21),
			Transaction("Turing","1/11/2002",66.10),
			Transaction("Thompson","2/27/2000",4747.08),
			Transaction("Turing","2/11/1991",2156.86),
			Transaction("Hoare","8/12/2003",1025.70),
			Transaction("vonNeumann","10/13/1993",2520.97),
			Transaction("Dijkstra", "9/10/2000", 708.95),
			Transaction("Turing", "10/12/1993", 3532.36),
			Transaction("Hoare", "2/10/2005", 4050.20)
		)
	}
	
	def in1: String = {
		"""This is"""
	}
	
	def in2: Array[String] = {
		Array("a tiny","test.")
	}
	
	def cards: Array[String] = {
		Array("2C 3C 4C 5C 6C 7C 8C 9C 10C JC QC KC AC",
				"2D 3D 4D 5D 6D 7D 8D 9D 10D JD QD KD AD",
				"2H 3H 4H 5H 6H 7H 8H 9H 10H JH QH KH AH",
				"2S 3S 4S 5S 6S 7S 8S 9S 10S JS QS KS AS")
	}
	
	def toBe: String = {
		"""to be or not to - be - - that - - - is"""
	}
	
	def get1Kints(): Stream[Int] = {
		resourceAsIntStream("1Kints.txt")
	}
	
	def tinyT: Array[Int] = {
		Array(23,50,10,99,18,23,98,84,11,10,48,77,13,54,98,77,77,68)
	}
	
	def tinyW: Array[Int] = {
		Array(84,48,68,10,18,98,12,23,54,57,48,33,16,77,11,29)
	}
	
	case class UnionFindDTO(n: Int, pairs: Seq[(Int,Int)])
	
	def tinyUnionFind: UnionFindDTO = {
		val pairs = Seq(
			(4,3),
			(3,8),
			(6,5),
			(9,4),
			(2,1),
			(8,9),
			(5,0),
			(7,2),
			(6,1),
			(1,0),
			(6,0)
		)
		UnionFindDTO(10, pairs)
	}
	
	def mediumUnionFind: UnionFindDTO = {
		val pairs = Seq(
			(528,503),
			(548,523),
			(389,414),
			(446,421),
			(552,553),
			(154,155),
			(173,174),
			(373,348),
			(567,542),
			(44,43),
			(370,345),
			(546,547),
			(204,229),
			(404,429),
			(240,215),
			(364,389),
			(612,611),
			(513,512),
			(377,376),
			(468,443),
			(410,435),
			(243,218),
			(347,322),
			(580,581),
			(188,163),
			(61,36),
			(545,546),
			(93,68),
			(84,83),
			(94,69),
			(7,8),
			(619,618),
			(314,339),
			(155,156),
			(150,175),
			(605,580),
			(118,93),
			(385,360),
			(459,458),
			(167,168),
			(107,108),
			(44,69),
			(335,334),
			(251,276),
			(196,197),
			(501,502),
			(212,187),
			(251,250),
			(269,270),
			(332,331),
			(125,150),
			(391,416),
			(366,367),
			(65,40),
			(515,540),
			(248,273),
			(34,9),
			(480,479),
			(198,173),
			(463,488),
			(111,86),
			(524,499),
			(28,27),
			(323,324),
			(198,199),
			(146,147),
			(133,158),
			(416,415),
			(103,102),
			(457,482),
			(57,82),
			(88,113),
			(535,560),
			(181,180),
			(605,606),
			(481,456),
			(127,102),
			(470,445),
			(229,254),
			(169,170),
			(386,385),
			(383,384),
			(153,152),
			(541,542),
			(36,37),
			(474,473),
			(126,125),
			(534,509),
			(154,129),
			(591,592),
			(161,186),
			(209,234),
			(88,87),
			(61,60),
			(161,136),
			(472,447),
			(239,240),
			(102,101),
			(342,343),
			(566,565),
			(567,568),
			(41,42),
			(154,153),
			(471,496),
			(358,383),
			(423,448),
			(241,242),
			(292,293),
			(363,364),
			(361,362),
			(258,283),
			(75,100),
			(61,86),
			(81,106),
			(52,27),
			(230,255),
			(309,334),
			(378,379),
			(136,111),
			(439,464),
			(532,533),
			(166,191),
			(523,522),
			(210,211),
			(115,140),
			(347,346),
			(218,217),
			(561,560),
			(526,501),
			(174,149),
			(258,259),
			(77,52),
			(36,11),
			(307,306),
			(577,552),
			(62,61),
			(450,425),
			(569,570),
			(268,293),
			(79,78),
			(233,208),
			(571,570),
			(534,535),
			(527,552),
			(224,199),
			(409,408),
			(521,520),
			(621,622),
			(493,518),
			(107,106),
			(511,510),
			(298,299),
			(37,62),
			(224,249),
			(405,380),
			(236,237),
			(120,121),
			(393,418),
			(206,231),
			(287,288),
			(593,568),
			(34,59),
			(483,484),
			(226,227),
			(73,74),
			(276,277),
			(588,587),
			(288,313),
			(410,385),
			(506,505),
			(597,598),
			(337,312),
			(55,56),
			(300,325),
			(135,134),
			(4,29),
			(501,500),
			(438,437),
			(311,312),
			(598,599),
			(320,345),
			(211,236),
			(587,562),
			(74,99),
			(473,498),
			(278,279),
			(394,369),
			(123,148),
			(233,232),
			(252,277),
			(177,202),
			(160,185),
			(331,356),
			(192,191),
			(119,118),
			(576,601),
			(317,316),
			(462,487),
			(42,43),
			(336,311),
			(515,490),
			(13,14),
			(210,235),
			(473,448),
			(342,341),
			(340,315),
			(413,388),
			(514,515),
			(144,143),
			(146,145),
			(541,566),
			(128,103),
			(184,159),
			(488,489),
			(454,455),
			(82,83),
			(70,45),
			(221,222),
			(241,240),
			(412,411),
			(591,590),
			(592,593),
			(276,301),
			(452,453),
			(256,255),
			(397,372),
			(201,200),
			(232,207),
			(466,465),
			(561,586),
			(417,442),
			(409,434),
			(238,239),
			(389,390),
			(26,1),
			(510,485),
			(283,282),
			(281,306),
			(449,474),
			(324,349),
			(121,146),
			(111,112),
			(434,435),
			(507,508),
			(103,104),
			(319,294),
			(455,480),
			(558,557),
			(291,292),
			(553,578),
			(392,391),
			(552,551),
			(55,80),
			(538,539),
			(367,392),
			(340,365),
			(272,297),
			(266,265),
			(401,376),
			(279,280),
			(516,515),
			(178,177),
			(572,571),
			(154,179),
			(263,262),
			(6,31),
			(323,348),
			(481,506),
			(178,179),
			(526,527),
			(444,469),
			(273,274),
			(132,133),
			(275,300),
			(261,236),
			(344,369),
			(63,38),
			(5,30),
			(301,300),
			(86,87),
			(9,10),
			(344,319),
			(428,427),
			(400,375),
			(350,375),
			(235,236),
			(337,336),
			(616,615),
			(381,380),
			(58,59),
			(492,493),
			(555,556),
			(459,434),
			(368,369),
			(407,382),
			(166,141),
			(70,95),
			(380,355),
			(34,35),
			(49,24),
			(126,127),
			(403,378),
			(509,484),
			(613,588),
			(208,207),
			(143,168),
			(406,431),
			(263,238),
			(595,596),
			(218,193),
			(183,182),
			(195,220),
			(381,406),
			(64,65),
			(371,372),
			(531,506),
			(218,219),
			(144,145),
			(475,450),
			(547,548),
			(363,362),
			(337,362),
			(214,239),
			(110,111),
			(600,575),
			(105,106),
			(147,148),
			(599,574),
			(622,623),
			(319,320),
			(36,35),
			(258,233),
			(266,267),
			(481,480),
			(414,439),
			(169,168),
			(479,478),
			(224,223),
			(181,182),
			(351,326),
			(466,441),
			(85,60),
			(140,165),
			(91,90),
			(263,264),
			(188,187),
			(446,447),
			(607,606),
			(341,316),
			(143,142),
			(443,442),
			(354,353),
			(162,137),
			(281,256),
			(549,574),
			(407,408),
			(575,550),
			(171,170),
			(389,388),
			(390,391),
			(250,225),
			(536,537),
			(227,228),
			(84,59),
			(139,140),
			(485,484),
			(573,598),
			(356,381),
			(314,315),
			(299,324),
			(370,395),
			(166,165),
			(63,62),
			(507,506),
			(426,425),
			(479,454),
			(545,570),
			(376,375),
			(572,597),
			(606,581),
			(278,277),
			(303,302),
			(190,165),
			(230,205),
			(175,200),
			(529,528),
			(18,17),
			(458,457),
			(514,513),
			(617,616),
			(298,323),
			(162,161),
			(471,472),
			(81,56),
			(182,207),
			(539,564),
			(573,572),
			(596,621),
			(64,39),
			(571,546),
			(554,555),
			(388,363),
			(351,376),
			(304,329),
			(123,122),
			(135,160),
			(157,132),
			(599,624),
			(451,426),
			(162,187),
			(502,477),
			(508,483),
			(141,140),
			(303,328),
			(551,576),
			(471,446),
			(161,160),
			(465,490),
			(3,2),
			(138,113),
			(309,284),
			(452,451),
			(414,413),
			(540,565),
			(210,185),
			(350,325),
			(383,382),
			(2,1),
			(598,623),
			(97,72),
			(485,460),
			(315,316),
			(19,20),
			(31,32),
			(546,521),
			(320,321),
			(29,54),
			(330,331),
			(92,67),
			(480,505),
			(274,249),
			(22,47),
			(304,279),
			(493,468),
			(424,423),
			(39,40),
			(164,165),
			(269,268),
			(445,446),
			(228,203),
			(384,409),
			(390,365),
			(283,308),
			(374,399),
			(361,386),
			(94,119),
			(237,262),
			(43,68),
			(295,270),
			(400,425),
			(360,335),
			(122,121),
			(469,468),
			(189,188),
			(377,352),
			(367,342),
			(67,42),
			(616,591),
			(442,467),
			(558,533),
			(395,394),
			(3,28),
			(476,477),
			(257,258),
			(280,281),
			(517,542),
			(505,504),
			(302,301),
			(14,15),
			(523,498),
			(393,368),
			(46,71),
			(141,142),
			(477,452),
			(535,510),
			(237,238),
			(232,231),
			(5,6),
			(75,50),
			(278,253),
			(68,69),
			(584,559),
			(503,504),
			(281,282),
			(19,44),
			(411,410),
			(290,265),
			(579,554),
			(85,84),
			(65,66),
			(9,8),
			(484,459),
			(427,402),
			(195,196),
			(617,618),
			(418,443),
			(101,126),
			(268,243),
			(92,117),
			(290,315),
			(562,561),
			(255,280),
			(488,487),
			(578,603),
			(80,79),
			(57,58),
			(77,78),
			(417,418),
			(246,271),
			(95,96),
			(234,233),
			(530,555),
			(543,568),
			(396,397),
			(22,23),
			(29,28),
			(502,527),
			(12,13),
			(217,216),
			(522,547),
			(357,332),
			(543,518),
			(151,176),
			(69,70),
			(556,557),
			(247,248),
			(513,538),
			(204,205),
			(604,605),
			(528,527),
			(455,456),
			(624,623),
			(284,285),
			(27,26),
			(94,95),
			(486,511),
			(192,167),
			(372,347),
			(129,104),
			(349,374),
			(313,314),
			(354,329),
			(294,293),
			(377,378),
			(291,290),
			(433,408),
			(57,56),
			(215,190),
			(467,492),
			(383,408),
			(569,594),
			(209,208),
			(2,27),
			(466,491),
			(147,122),
			(112,113),
			(21,46),
			(284,259),
			(563,538),
			(392,417),
			(458,433),
			(464,465),
			(297,298),
			(336,361),
			(607,582),
			(553,554),
			(225,200),
			(186,211),
			(33,34),
			(237,212),
			(52,51),
			(620,595),
			(492,517),
			(585,610),
			(257,282),
			(520,545),
			(541,540),
			(269,244),
			(609,584),
			(109,84),
			(247,246),
			(562,537),
			(172,197),
			(166,167),
			(264,265),
			(129,130),
			(89,114),
			(204,179),
			(51,76),
			(415,390),
			(54,53),
			(219,244),
			(491,490),
			(494,493),
			(87,62),
			(158,183),
			(517,518),
			(358,359),
			(105,104),
			(285,260),
			(343,318),
			(348,347),
			(615,614),
			(169,144),
			(53,78),
			(494,495),
			(576,577),
			(23,24),
			(22,21),
			(41,40),
			(467,466),
			(112,87),
			(245,220),
			(442,441),
			(411,436),
			(256,257),
			(469,494),
			(441,416),
			(132,107),
			(468,467),
			(345,344),
			(608,609),
			(358,333),
			(418,419),
			(430,429),
			(130,131),
			(127,128),
			(115,90),
			(364,365),
			(296,271),
			(260,235),
			(229,228),
			(232,257),
			(189,190),
			(234,235),
			(195,170),
			(117,118),
			(487,486),
			(203,204),
			(142,117),
			(582,583),
			(561,536),
			(7,32),
			(387,388),
			(333,334),
			(420,421),
			(317,292),
			(327,352),
			(564,563),
			(39,14),
			(177,152),
			(144,119),
			(426,401),
			(248,223),
			(566,567),
			(53,28),
			(106,131),
			(473,472),
			(525,526),
			(327,302),
			(382,381),
			(222,197),
			(610,609),
			(522,521),
			(291,316),
			(339,338),
			(328,329),
			(31,56),
			(247,222),
			(185,186),
			(554,529),
			(393,392),
			(108,83),
			(514,489),
			(48,23),
			(37,12),
			(46,45),
			(25,0),
			(463,462),
			(101,76),
			(11,10),
			(548,573),
			(137,112),
			(123,124),
			(359,360),
			(489,490),
			(368,367),
			(71,96),
			(229,230),
			(496,495),
			(366,365),
			(86,85),
			(496,497),
			(482,481),
			(326,301),
			(278,303),
			(139,114),
			(71,70),
			(275,276),
			(223,198),
			(590,565),
			(496,521),
			(16,41),
			(501,476),
			(371,370),
			(511,536),
			(577,602),
			(37,38),
			(423,422),
			(71,72),
			(399,424),
			(171,146),
			(32,33),
			(157,182),
			(608,583),
			(474,499),
			(205,206),
			(539,514),
			(601,600),
			(419,420),
			(208,183),
			(537,538),
			(110,85),
			(105,130),
			(288,289),
			(455,430),
			(531,532),
			(337,338),
			(227,202),
			(120,145),
			(559,534),
			(261,262),
			(241,216),
			(379,354),
			(430,405),
			(241,266),
			(396,421),
			(317,318),
			(139,164),
			(310,285),
			(478,477),
			(532,557),
			(238,213),
			(195,194),
			(359,384),
			(243,242),
			(432,457),
			(422,447),
			(519,518),
			(271,272),
			(12,11),
			(478,453),
			(453,428),
			(614,613),
			(138,139),
			(96,97),
			(399,398),
			(55,54),
			(199,174),
			(566,591),
			(213,188),
			(488,513),
			(169,194),
			(603,602),
			(293,318),
			(432,431),
			(524,523),
			(30,31),
			(88,63),
			(172,173),
			(510,509),
			(272,273),
			(559,558),
			(494,519),
			(374,373),
			(547,572),
			(263,288),
			(17,16),
			(78,103),
			(542,543),
			(131,132),
			(519,544),
			(504,529),
			(60,59),
			(356,355),
			(341,340),
			(415,414),
			(285,286),
			(439,438),
			(588,563),
			(25,50),
			(463,438),
			(581,556),
			(244,245),
			(500,475),
			(93,92),
			(274,299),
			(351,350),
			(152,127),
			(472,497),
			(440,415),
			(214,215),
			(231,230),
			(80,81),
			(550,525),
			(511,512),
			(483,458),
			(67,68),
			(255,254),
			(589,588),
			(147,172),
			(454,453),
			(587,612),
			(343,368),
			(508,509),
			(240,265),
			(49,48),
			(184,183),
			(583,558),
			(164,189),
			(461,436),
			(109,134),
			(196,171),
			(156,181),
			(124,99),
			(531,530),
			(116,91),
			(431,430),
			(326,325),
			(44,45),
			(507,482),
			(557,582),
			(519,520),
			(167,142),
			(469,470),
			(563,562),
			(507,532),
			(94,93),
			(3,4),
			(366,391),
			(456,431),
			(524,549),
			(489,464),
			(397,398),
			(98,97),
			(377,402),
			(413,412),
			(148,149),
			(91,66),
			(308,333),
			(16,15),
			(312,287),
			(212,211),
			(486,461),
			(571,596),
			(226,251),
			(356,357),
			(145,170),
			(295,294),
			(308,309),
			(163,138),
			(364,339),
			(416,417),
			(402,401),
			(302,277),
			(349,348),
			(582,581),
			(176,175),
			(254,279),
			(589,614),
			(322,297),
			(587,586),
			(221,246),
			(526,551),
			(159,158),
			(460,461),
			(452,427),
			(329,330),
			(321,322),
			(82,107),
			(462,461),
			(495,520),
			(303,304),
			(90,65),
			(295,320),
			(160,159),
			(463,464),
			(10,35),
			(619,594),
			(403,402)
		)
		UnionFindDTO(625, pairs)
	}
	
}