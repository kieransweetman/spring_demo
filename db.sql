--
-- PostgreSQL database dump
--

-- Dumped from database version 16.3 (Homebrew)
-- Dumped by pg_dump version 16.0

-- Started on 2024-08-21 11:56:34 CEST

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 3678 (class 0 OID 16399)
-- Dependencies: 216
-- Data for Name: city; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.city (id, name, population, "departmentId", "regionId", department_id, region_id) FROM stdin;
1	Paris	2148327	\N	\N	24	11
2	Marseille	861635	\N	\N	25	93
3	Lyon	515695	\N	\N	26	84
4	Toulouse	479553	\N	\N	27	76
5	Nantes	314138	\N	\N	29	52
6	Strasbourg	280966	\N	\N	30	44
7	Montpellier	277639	\N	\N	31	76
8	Bordeaux	252040	\N	\N	32	75
9	Lille	232787	\N	\N	33	32
10	Rennes	216815	\N	\N	34	53
11	Reims	182592	\N	\N	35	44
12	Saint-Étienne	172565	\N	\N	37	84
13	Toulon	171953	\N	\N	38	93
14	Le Havre	170147	\N	\N	36	32
15	Grenoble	158454	\N	\N	40	84
16	Dijon	155090	\N	\N	41	44
17	Angers	151229	\N	\N	39	52
18	Nîmes	150672	\N	\N	42	76
19	Villeurbanne	150659	\N	\N	26	84
20	Clermont-Ferrand	143886	\N	\N	43	84
21	Le Mans	143252	\N	\N	44	52
22	Aix-en-Provence	143006	\N	\N	25	93
23	Brest	139602	\N	\N	46	53
\.


--
-- TOC entry 3682 (class 0 OID 16413)
-- Dependencies: 220
-- Data for Name: department; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.department (id, code) FROM stdin;
24	75
25	13
26	69
27	31
28	6
29	44
30	67
31	34
32	33
33	59
34	35
35	51
36	76
37	42
38	83
39	49
40	38
41	21
42	30
43	2
44	87
45	37
46	29
\.


--
-- TOC entry 3680 (class 0 OID 16406)
-- Dependencies: 218
-- Data for Name: region; Type: TABLE DATA; Schema: public; Owner: -
--

COPY public.region (id, name) FROM stdin;
11	Île-de-France
93	Provence-Alpes-Côte d'Azur
84	Auvergne-Rhône-Alpes
76	Occitanie
52	Pays de la Loire
44	Grand Est
75	Nouvelle-Aquitaine
32	Hauts-de-France
53	Bretagne
24	Centre-Val de Loire
\.


--
-- TOC entry 3691 (class 0 OID 0)
-- Dependencies: 215
-- Name: City_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public."City_id_seq"', 1, false);


--
-- TOC entry 3692 (class 0 OID 0)
-- Dependencies: 219
-- Name: Department_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public."Department_id_seq"', 46, true);


--
-- TOC entry 3693 (class 0 OID 0)
-- Dependencies: 217
-- Name: Region_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('public."Region_id_seq"', 1, false);


-- Completed on 2024-08-21 11:56:34 CEST

--
-- PostgreSQL database dump complete
--

