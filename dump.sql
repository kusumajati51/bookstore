--
-- PostgreSQL database dump
--

-- Dumped from database version 16.8 (Ubuntu 16.8-1.pgdg22.04+1)
-- Dumped by pg_dump version 17.4 (Ubuntu 17.4-1.pgdg22.04+2)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: bookstore; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE bookstore WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'en_US.UTF-8';


ALTER DATABASE bookstore OWNER TO postgres;

\connect bookstore

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: books; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.books (
                              id bigint NOT NULL,
                              base_price double precision NOT NULL,
                              created_at timestamp(6) without time zone,
                              is_deleted boolean NOT NULL,
                              title character varying(255) NOT NULL,
                              type character varying(255) NOT NULL,
                              updated_at timestamp(6) without time zone,
                              CONSTRAINT books_type_check CHECK (((type)::text = ANY ((ARRAY['NEW_RELEASE'::character varying, 'REGULAR'::character varying, 'OLD_EDITION'::character varying])::text[])))
);


ALTER TABLE public.books OWNER TO postgres;

--
-- Name: books_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.books ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.books_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: point_usages; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.point_usages (
                                     id bigint NOT NULL,
                                     points_used integer NOT NULL,
                                     reason character varying(255) NOT NULL,
                                     used_at timestamp(6) without time zone NOT NULL,
                                     user_id bigint NOT NULL
);


ALTER TABLE public.point_usages OWNER TO postgres;

--
-- Name: point_usages_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.point_usages ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.point_usages_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: purchases; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.purchases (
                                  id bigint NOT NULL,
                                  purchased_at timestamp(6) without time zone NOT NULL,
                                  quantity integer NOT NULL,
                                  total_price double precision NOT NULL,
                                  book_id bigint NOT NULL,
                                  user_id bigint NOT NULL
);


ALTER TABLE public.purchases OWNER TO postgres;

--
-- Name: purchases_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.purchases ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.purchases_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: reward_redemptions; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.reward_redemptions (
                                           id bigint NOT NULL,
                                           points_used integer NOT NULL,
                                           redeemed_at timestamp(6) without time zone NOT NULL,
                                           reward_id bigint NOT NULL,
                                           user_id bigint NOT NULL
);


ALTER TABLE public.reward_redemptions OWNER TO postgres;

--
-- Name: reward_redemptions_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.reward_redemptions ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.reward_redemptions_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: rewards; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.rewards (
                                id bigint NOT NULL,
                                created_at timestamp(6) without time zone,
                                is_active boolean NOT NULL,
                                is_deleted boolean NOT NULL,
                                required_points integer NOT NULL,
                                stock integer NOT NULL,
                                title character varying(255) NOT NULL,
                                updated_at timestamp(6) without time zone,
                                book_id bigint
);


ALTER TABLE public.rewards OWNER TO postgres;

--
-- Name: rewards_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.rewards ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.rewards_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
                              id bigint NOT NULL,
                              created_at timestamp(6) without time zone,
                              email character varying(255) NOT NULL,
                              name character varying(255) NOT NULL,
                              password character varying(255) NOT NULL,
                              updated_at timestamp(6) without time zone,
                              is_admin boolean DEFAULT false NOT NULL
);


ALTER TABLE public.users OWNER TO postgres;

--
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.users ALTER COLUMN id ADD GENERATED BY DEFAULT AS IDENTITY (
    SEQUENCE NAME public.users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Data for Name: books; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.books (id, base_price, created_at, is_deleted, title, type, updated_at) FROM stdin;
1	10000	2025-04-19 23:24:50.661225	f	Jacker	NEW_RELEASE	2025-04-19 23:27:22.546514
2	10000	2025-04-19 23:27:32.189266	t	LORD	NEW_RELEASE	2025-04-19 23:28:37.819449
3	10000	2025-04-19 23:35:49.559762	f	LORD OF THE RINGS	NEW_RELEASE	\N
4	10000	2025-04-22 15:44:55.994089	f	SHAPIENS	NEW_RELEASE	\N
5	10000	2025-04-22 15:47:04.965603	f	LOGIKA MISTIKA	OLD_EDITION	\N
6	10000	2025-04-22 15:47:27.476488	f	Marmut Merah Jambu	OLD_EDITION	\N
7	10000	2025-04-22 15:48:06.79947	f	Men Are from Mars, Women Are from Venus	REGULAR	\N
8	10000	2025-04-22 21:59:44.346716	f	Jojo Bizare Advanture	OLD_EDITION	\N
\.


--
-- Data for Name: point_usages; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.point_usages (id, points_used, reason, used_at, user_id) FROM stdin;
1	10	EARN	2025-04-21 13:37:25.108454	1
2	2	EARN	2025-04-22 15:48:32.968543	1
3	10	REDEEM	2025-04-22 16:16:29.642152	1
4	12	EARN	2025-04-22 22:04:37.294024	1
5	10	REDEEM	2025-04-22 22:35:36.096252	1
\.


--
-- Data for Name: purchases; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.purchases (id, purchased_at, quantity, total_price, book_id, user_id) FROM stdin;
1	2025-04-20 20:15:00.538929	1	10000	1	1
2	2025-04-21 13:37:25.051455	1	10000	1	1
3	2025-04-22 15:48:32.911475	1	10000	5	1
4	2025-04-22 22:04:37.21091	6	60000	5	1
\.


--
-- Data for Name: reward_redemptions; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.reward_redemptions (id, points_used, redeemed_at, reward_id, user_id) FROM stdin;
1	10	2025-04-22 16:16:29.662973	1	1
2	10	2025-04-22 22:35:36.155238	1	1
\.


--
-- Data for Name: rewards; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.rewards (id, created_at, is_active, is_deleted, required_points, stock, title, updated_at, book_id) FROM stdin;
3	2025-04-22 15:58:55.694705	t	f	30	1	HAdiah 3	\N	3
4	2025-04-22 16:02:01.368538	t	f	40	1	HAdiah 4	\N	4
5	2025-04-22 16:02:17.480781	t	f	50	1	HAdiah 5	\N	5
2	2025-04-22 15:58:34.414826	f	t	10	1	Hadiah 2 Uncompleter	2025-04-22 16:11:10.677651	2
6	2025-04-22 22:07:20.89595	t	f	50	1	HAdiah 5	\N	5
7	2025-04-22 22:23:32.636057	t	t	50	1	HAdiah 5	2025-04-22 22:26:23.756984	5
1	2025-04-22 15:58:26.20306	t	f	10	0	Hadiah 1	2025-04-22 22:35:36.03014	1
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (id, created_at, email, name, password, updated_at, is_admin) FROM stdin;
1	2025-04-18 20:22:48.411905	tester@main.com	John Doe	$2a$10$rd9QzNUB.pOJhON21t2geuXmWTVBJmBVDM9hM73cyHpGuN6.F6qP6	\N	f
4	2025-04-19 16:29:58.763194	tester2@main.com	John Doe	$2a$10$liFH2OVfxILWdhArOZAMnOievxE2QDuSQ0Psr7mbgb/ux9aJeiCWC	\N	f
5	2025-04-22 21:30:35.927702	tester12@main.com	John Doe	$2a$10$ndpi1RRrW5klmKYfsTXaK.EESlAstdHqLituLBwuBhWnDMSz9bmMK	\N	f
6	2025-04-22 21:32:36.570369	admin@mail.com	Admin	$2a$10$miazTCFD4iYHG7NtDLBaIenoKIgt3t7a1VjjQxroA7ma6dxPYAXCu	\N	t
\.


--
-- Name: books_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.books_id_seq', 8, true);


--
-- Name: point_usages_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.point_usages_id_seq', 5, true);


--
-- Name: purchases_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.purchases_id_seq', 4, true);


--
-- Name: reward_redemptions_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.reward_redemptions_id_seq', 2, true);


--
-- Name: rewards_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.rewards_id_seq', 7, true);


--
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_id_seq', 6, true);


--
-- Name: books books_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.books
    ADD CONSTRAINT books_pkey PRIMARY KEY (id);


--
-- Name: point_usages point_usages_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.point_usages
    ADD CONSTRAINT point_usages_pkey PRIMARY KEY (id);


--
-- Name: purchases purchases_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.purchases
    ADD CONSTRAINT purchases_pkey PRIMARY KEY (id);


--
-- Name: reward_redemptions reward_redemptions_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reward_redemptions
    ADD CONSTRAINT reward_redemptions_pkey PRIMARY KEY (id);


--
-- Name: rewards rewards_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rewards
    ADD CONSTRAINT rewards_pkey PRIMARY KEY (id);


--
-- Name: users uk6dotkott2kjsp8vw4d0m25fb7; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT uk6dotkott2kjsp8vw4d0m25fb7 UNIQUE (email);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- Name: reward_redemptions fk1fl7vw3n1k600ewfdmcaoau12; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reward_redemptions
    ADD CONSTRAINT fk1fl7vw3n1k600ewfdmcaoau12 FOREIGN KEY (reward_id) REFERENCES public.rewards(id);


--
-- Name: purchases fk4wsc9h3nuk217sy5lp4bg2888; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.purchases
    ADD CONSTRAINT fk4wsc9h3nuk217sy5lp4bg2888 FOREIGN KEY (book_id) REFERENCES public.books(id);


--
-- Name: reward_redemptions fk7mlsj71t0guywfoblp1o3q56a; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.reward_redemptions
    ADD CONSTRAINT fk7mlsj71t0guywfoblp1o3q56a FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: rewards fke8ot8uct3fbppfhury71l5vpu; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.rewards
    ADD CONSTRAINT fke8ot8uct3fbppfhury71l5vpu FOREIGN KEY (book_id) REFERENCES public.books(id);


--
-- Name: purchases fkm0ndjymn9p747pfp4515pio8i; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.purchases
    ADD CONSTRAINT fkm0ndjymn9p747pfp4515pio8i FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: point_usages fkosjd8i1umo9k9vkdgyaj30m99; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.point_usages
    ADD CONSTRAINT fkosjd8i1umo9k9vkdgyaj30m99 FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- PostgreSQL database dump complete
--

