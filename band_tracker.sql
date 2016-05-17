--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: bands; Type: TABLE; Schema: public; Owner: scout; Tablespace: 
--

CREATE TABLE bands (
    id integer NOT NULL,
    name character varying,
    genre character varying
);


ALTER TABLE public.bands OWNER TO scout;

--
-- Name: bands_id_seq; Type: SEQUENCE; Schema: public; Owner: scout
--

CREATE SEQUENCE bands_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.bands_id_seq OWNER TO scout;

--
-- Name: bands_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: scout
--

ALTER SEQUENCE bands_id_seq OWNED BY bands.id;


--
-- Name: bands_venues; Type: TABLE; Schema: public; Owner: scout; Tablespace: 
--

CREATE TABLE bands_venues (
    id integer NOT NULL,
    band_id integer,
    venue_id integer,
    date character varying
);


ALTER TABLE public.bands_venues OWNER TO scout;

--
-- Name: bands_venues_id_seq; Type: SEQUENCE; Schema: public; Owner: scout
--

CREATE SEQUENCE bands_venues_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.bands_venues_id_seq OWNER TO scout;

--
-- Name: bands_venues_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: scout
--

ALTER SEQUENCE bands_venues_id_seq OWNED BY bands_venues.id;


--
-- Name: venues; Type: TABLE; Schema: public; Owner: scout; Tablespace: 
--

CREATE TABLE venues (
    id integer NOT NULL,
    name character varying,
    number character varying,
    city character varying,
    state character varying
);


ALTER TABLE public.venues OWNER TO scout;

--
-- Name: venues_id_seq; Type: SEQUENCE; Schema: public; Owner: scout
--

CREATE SEQUENCE venues_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.venues_id_seq OWNER TO scout;

--
-- Name: venues_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: scout
--

ALTER SEQUENCE venues_id_seq OWNED BY venues.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: scout
--

ALTER TABLE ONLY bands ALTER COLUMN id SET DEFAULT nextval('bands_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: scout
--

ALTER TABLE ONLY bands_venues ALTER COLUMN id SET DEFAULT nextval('bands_venues_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: scout
--

ALTER TABLE ONLY venues ALTER COLUMN id SET DEFAULT nextval('venues_id_seq'::regclass);


--
-- Data for Name: bands; Type: TABLE DATA; Schema: public; Owner: scout
--

COPY bands (id, name, genre) FROM stdin;
\.


--
-- Name: bands_id_seq; Type: SEQUENCE SET; Schema: public; Owner: scout
--

SELECT pg_catalog.setval('bands_id_seq', 13, true);


--
-- Data for Name: bands_venues; Type: TABLE DATA; Schema: public; Owner: scout
--

COPY bands_venues (id, band_id, venue_id, date) FROM stdin;
\.


--
-- Name: bands_venues_id_seq; Type: SEQUENCE SET; Schema: public; Owner: scout
--

SELECT pg_catalog.setval('bands_venues_id_seq', 4, true);


--
-- Data for Name: venues; Type: TABLE DATA; Schema: public; Owner: scout
--

COPY venues (id, name, number, city, state) FROM stdin;
\.


--
-- Name: venues_id_seq; Type: SEQUENCE SET; Schema: public; Owner: scout
--

SELECT pg_catalog.setval('venues_id_seq', 11, true);


--
-- Name: bands_pkey; Type: CONSTRAINT; Schema: public; Owner: scout; Tablespace: 
--

ALTER TABLE ONLY bands
    ADD CONSTRAINT bands_pkey PRIMARY KEY (id);


--
-- Name: bands_venues_pkey; Type: CONSTRAINT; Schema: public; Owner: scout; Tablespace: 
--

ALTER TABLE ONLY bands_venues
    ADD CONSTRAINT bands_venues_pkey PRIMARY KEY (id);


--
-- Name: venues_pkey; Type: CONSTRAINT; Schema: public; Owner: scout; Tablespace: 
--

ALTER TABLE ONLY venues
    ADD CONSTRAINT venues_pkey PRIMARY KEY (id);


--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

