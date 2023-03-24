--
-- PostgreSQL database dump
--

-- Dumped from database version 15.2
-- Dumped by pg_dump version 15.2

-- Started on 2023-03-24 11:42:50

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

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 223 (class 1259 OID 16557)
-- Name: categories; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.categories (
    cat_name character varying(50) NOT NULL
);


ALTER TABLE public.categories OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 16526)
-- Name: persons; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.persons (
    id integer NOT NULL,
    first_name character varying(50) NOT NULL,
    last_name character varying(50) NOT NULL,
    email character varying(250) NOT NULL,
    address character varying(500),
    phone_number character varying(20)
);


ALTER TABLE public.persons OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 16525)
-- Name: persons_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.persons_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.persons_id_seq OWNER TO postgres;

--
-- TOC entry 3389 (class 0 OID 0)
-- Dependencies: 216
-- Name: persons_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.persons_id_seq OWNED BY public.persons.id;


--
-- TOC entry 226 (class 1259 OID 16564)
-- Name: products; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.products (
    id integer NOT NULL,
    product_label character varying(50) NOT NULL,
    description character varying(500) NOT NULL,
    price numeric(10,2) NOT NULL,
    picture character varying(500) NOT NULL,
    sales_id integer NOT NULL,
    category_name character varying(50) NOT NULL
);


ALTER TABLE public.products OWNER TO postgres;

--
-- TOC entry 224 (class 1259 OID 16562)
-- Name: products_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.products_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.products_id_seq OWNER TO postgres;

--
-- TOC entry 3390 (class 0 OID 0)
-- Dependencies: 224
-- Name: products_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.products_id_seq OWNED BY public.products.id;


--
-- TOC entry 225 (class 1259 OID 16563)
-- Name: products_salesid_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.products_salesid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.products_salesid_seq OWNER TO postgres;

--
-- TOC entry 3391 (class 0 OID 0)
-- Dependencies: 225
-- Name: products_salesid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.products_salesid_seq OWNED BY public.products.sales_id;


--
-- TOC entry 227 (class 1259 OID 16584)
-- Name: roles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.roles (
    role_name character varying(50) NOT NULL
);


ALTER TABLE public.roles OWNER TO postgres;

--
-- TOC entry 222 (class 1259 OID 16549)
-- Name: sales; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.sales (
    id integer NOT NULL,
    on_date character varying(50) NOT NULL,
    off_date character varying(50) NOT NULL,
    discount integer NOT NULL,
    products_list character varying[]
);


ALTER TABLE public.sales OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 16548)
-- Name: sales_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.sales_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.sales_id_seq OWNER TO postgres;

--
-- TOC entry 3392 (class 0 OID 0)
-- Dependencies: 221
-- Name: sales_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.sales_id_seq OWNED BY public.sales.id;


--
-- TOC entry 215 (class 1259 OID 16517)
-- Name: tokens; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tokens (
    id integer NOT NULL,
    user_name_hash character varying(500) NOT NULL,
    validity character varying(100) NOT NULL,
    user_role character varying(50) NOT NULL
);


ALTER TABLE public.tokens OWNER TO postgres;

--
-- TOC entry 214 (class 1259 OID 16516)
-- Name: tokens_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.tokens_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tokens_id_seq OWNER TO postgres;

--
-- TOC entry 3393 (class 0 OID 0)
-- Dependencies: 214
-- Name: tokens_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.tokens_id_seq OWNED BY public.tokens.id;


--
-- TOC entry 220 (class 1259 OID 16536)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    id integer NOT NULL,
    user_name character varying(50) NOT NULL,
    password_hash character varying(250) NOT NULL,
    salt character varying(100) NOT NULL,
    user_role character varying(50) NOT NULL,
    person_id integer NOT NULL
);


ALTER TABLE public.users OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 16534)
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.users_id_seq OWNER TO postgres;

--
-- TOC entry 3394 (class 0 OID 0)
-- Dependencies: 218
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;


--
-- TOC entry 219 (class 1259 OID 16535)
-- Name: users_personid_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.users_personid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.users_personid_seq OWNER TO postgres;

--
-- TOC entry 3395 (class 0 OID 0)
-- Dependencies: 219
-- Name: users_personid_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.users_personid_seq OWNED BY public.users.person_id;


--
-- TOC entry 3204 (class 2604 OID 16529)
-- Name: persons id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.persons ALTER COLUMN id SET DEFAULT nextval('public.persons_id_seq'::regclass);


--
-- TOC entry 3208 (class 2604 OID 16567)
-- Name: products id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.products ALTER COLUMN id SET DEFAULT nextval('public.products_id_seq'::regclass);


--
-- TOC entry 3209 (class 2604 OID 16568)
-- Name: products sales_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.products ALTER COLUMN sales_id SET DEFAULT nextval('public.products_salesid_seq'::regclass);


--
-- TOC entry 3207 (class 2604 OID 16552)
-- Name: sales id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sales ALTER COLUMN id SET DEFAULT nextval('public.sales_id_seq'::regclass);


--
-- TOC entry 3203 (class 2604 OID 16520)
-- Name: tokens id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tokens ALTER COLUMN id SET DEFAULT nextval('public.tokens_id_seq'::regclass);


--
-- TOC entry 3205 (class 2604 OID 16539)
-- Name: users id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);


--
-- TOC entry 3206 (class 2604 OID 16540)
-- Name: users person_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users ALTER COLUMN person_id SET DEFAULT nextval('public.users_personid_seq'::regclass);


--
-- TOC entry 3379 (class 0 OID 16557)
-- Dependencies: 223
-- Data for Name: categories; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.categories (cat_name) FROM stdin;
Test
Bio
Viandes Poissons
Fruits Légumes
Pains Pâtisseries
Frais
Surgelé
Epicerie salée
Epicerie sucrée
Boisson
Bébé
Hygiène Beauté
Entretien Nettoyage
Jardin
High Tech
Maison
Textile
Electroménagé
Livre
\.


--
-- TOC entry 3373 (class 0 OID 16526)
-- Dependencies: 217
-- Data for Name: persons; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.persons (id, first_name, last_name, email, address, phone_number) FROM stdin;
1	John	Dupont	johndupont@email.fr	\N	\N
2	Magalie	Doe	magaliedoe@email.fr	\N	\N
\.


--
-- TOC entry 3382 (class 0 OID 16564)
-- Dependencies: 226
-- Data for Name: products; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.products (id, product_label, description, price, picture, sales_id, category_name) FROM stdin;
1	Test_1	produit test n°1	500.00	chemin/test/picture1	1	Test
2	Test_2	produit test n°2	500.00	chemin/test/picture2	1	Test
\.


--
-- TOC entry 3383 (class 0 OID 16584)
-- Dependencies: 227
-- Data for Name: roles; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.roles (role_name) FROM stdin;
admin
visitor
\.


--
-- TOC entry 3378 (class 0 OID 16549)
-- Dependencies: 222
-- Data for Name: sales; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.sales (id, on_date, off_date, discount, products_list) FROM stdin;
1	2023-02-15	2023-02-18	20	\N
2	2023-02-19	2023-02-27	30	\N
3	2023-03-15	2023-03-18	20	{1,2}
4	2023-03-19	2023-03-27	30	{1,2}
\.


--
-- TOC entry 3371 (class 0 OID 16517)
-- Dependencies: 215
-- Data for Name: tokens; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tokens (id, user_name_hash, validity, user_role) FROM stdin;
1	azertyuiopqsdfghjklm	2023-03-25	admin
\.


--
-- TOC entry 3376 (class 0 OID 16536)
-- Dependencies: 220
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (id, user_name, password_hash, salt, user_role, person_id) FROM stdin;
1	johndupont@email.fr	passJohnHash	azerty	admin	1
2	magaliedoe@email.fr	passMagalieHash	azerty	admin	2
\.


--
-- TOC entry 3396 (class 0 OID 0)
-- Dependencies: 216
-- Name: persons_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.persons_id_seq', 2, true);


--
-- TOC entry 3397 (class 0 OID 0)
-- Dependencies: 224
-- Name: products_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.products_id_seq', 2, true);


--
-- TOC entry 3398 (class 0 OID 0)
-- Dependencies: 225
-- Name: products_salesid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.products_salesid_seq', 1, true);


--
-- TOC entry 3399 (class 0 OID 0)
-- Dependencies: 221
-- Name: sales_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.sales_id_seq', 4, true);


--
-- TOC entry 3400 (class 0 OID 0)
-- Dependencies: 214
-- Name: tokens_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tokens_id_seq', 1, true);


--
-- TOC entry 3401 (class 0 OID 0)
-- Dependencies: 218
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_id_seq', 2, true);


--
-- TOC entry 3402 (class 0 OID 0)
-- Dependencies: 219
-- Name: users_personid_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_personid_seq', 1, false);


--
-- TOC entry 3219 (class 2606 OID 16561)
-- Name: categories categories_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.categories
    ADD CONSTRAINT categories_pkey PRIMARY KEY (cat_name);


--
-- TOC entry 3213 (class 2606 OID 16533)
-- Name: persons persons_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.persons
    ADD CONSTRAINT persons_pkey PRIMARY KEY (id);


--
-- TOC entry 3221 (class 2606 OID 16572)
-- Name: products products_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.products
    ADD CONSTRAINT products_pkey PRIMARY KEY (id);


--
-- TOC entry 3223 (class 2606 OID 16588)
-- Name: roles roles_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (role_name);


--
-- TOC entry 3217 (class 2606 OID 16556)
-- Name: sales sales_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sales
    ADD CONSTRAINT sales_pkey PRIMARY KEY (id);


--
-- TOC entry 3211 (class 2606 OID 16524)
-- Name: tokens tokens_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tokens
    ADD CONSTRAINT tokens_pkey PRIMARY KEY (id);


--
-- TOC entry 3215 (class 2606 OID 16542)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- TOC entry 3226 (class 2606 OID 16578)
-- Name: products fk_category; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.products
    ADD CONSTRAINT fk_category FOREIGN KEY (category_name) REFERENCES public.categories(cat_name);


--
-- TOC entry 3224 (class 2606 OID 16543)
-- Name: users fk_person; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT fk_person FOREIGN KEY (person_id) REFERENCES public.persons(id);


--
-- TOC entry 3225 (class 2606 OID 16589)
-- Name: users fk_role; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT fk_role FOREIGN KEY (user_role) REFERENCES public.roles(role_name);


--
-- TOC entry 3227 (class 2606 OID 16573)
-- Name: products fk_sale; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.products
    ADD CONSTRAINT fk_sale FOREIGN KEY (sales_id) REFERENCES public.sales(id);


-- Completed on 2023-03-24 11:42:50

--
-- PostgreSQL database dump complete
--

