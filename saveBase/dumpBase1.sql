PGDMP     (    4    	            {        	   mercadona    15.2    15.2 9    :           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            ;           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            <           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            =           1262    16398 	   mercadona    DATABASE     |   CREATE DATABASE mercadona WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'French_France.1252';
    DROP DATABASE mercadona;
                postgres    false            �            1259    16557 
   categories    TABLE     P   CREATE TABLE public.categories (
    cat_name character varying(50) NOT NULL
);
    DROP TABLE public.categories;
       public         heap    postgres    false            �            1259    16526    persons    TABLE       CREATE TABLE public.persons (
    id integer NOT NULL,
    first_name character varying(50) NOT NULL,
    last_name character varying(50) NOT NULL,
    email character varying(250) NOT NULL,
    address character varying(500),
    phone_number character varying(20)
);
    DROP TABLE public.persons;
       public         heap    postgres    false            �            1259    16525    persons_id_seq    SEQUENCE     �   CREATE SEQUENCE public.persons_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.persons_id_seq;
       public          postgres    false    217            >           0    0    persons_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.persons_id_seq OWNED BY public.persons.id;
          public          postgres    false    216            �            1259    16564    products    TABLE     4  CREATE TABLE public.products (
    id integer NOT NULL,
    product_label character varying(50) NOT NULL,
    description character varying(500) NOT NULL,
    price numeric(10,2) NOT NULL,
    picture character varying(500) NOT NULL,
    sales_id integer,
    category_name character varying(50) NOT NULL
);
    DROP TABLE public.products;
       public         heap    postgres    false            �            1259    16562    products_id_seq    SEQUENCE     �   CREATE SEQUENCE public.products_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.products_id_seq;
       public          postgres    false    226            ?           0    0    products_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.products_id_seq OWNED BY public.products.id;
          public          postgres    false    224            �            1259    16563    products_salesid_seq    SEQUENCE     �   CREATE SEQUENCE public.products_salesid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public.products_salesid_seq;
       public          postgres    false    226            @           0    0    products_salesid_seq    SEQUENCE OWNED BY     N   ALTER SEQUENCE public.products_salesid_seq OWNED BY public.products.sales_id;
          public          postgres    false    225            �            1259    16584    roles    TABLE     L   CREATE TABLE public.roles (
    role_name character varying(50) NOT NULL
);
    DROP TABLE public.roles;
       public         heap    postgres    false            �            1259    16549    sales    TABLE     �   CREATE TABLE public.sales (
    id integer NOT NULL,
    on_date character varying(50) NOT NULL,
    off_date character varying(50) NOT NULL,
    discount integer NOT NULL,
    products_list character varying(250)
);
    DROP TABLE public.sales;
       public         heap    postgres    false            �            1259    16548    sales_id_seq    SEQUENCE     �   CREATE SEQUENCE public.sales_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.sales_id_seq;
       public          postgres    false    222            A           0    0    sales_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.sales_id_seq OWNED BY public.sales.id;
          public          postgres    false    221            �            1259    16517    tokens    TABLE     �   CREATE TABLE public.tokens (
    id integer NOT NULL,
    user_name_hash character varying(500) NOT NULL,
    validity character varying(100) NOT NULL,
    user_role character varying(50) NOT NULL
);
    DROP TABLE public.tokens;
       public         heap    postgres    false            �            1259    16516    tokens_id_seq    SEQUENCE     �   CREATE SEQUENCE public.tokens_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.tokens_id_seq;
       public          postgres    false    215            B           0    0    tokens_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.tokens_id_seq OWNED BY public.tokens.id;
          public          postgres    false    214            �            1259    16536    users    TABLE       CREATE TABLE public.users (
    id integer NOT NULL,
    user_name character varying(50) NOT NULL,
    password_hash character varying(250) NOT NULL,
    salt character varying(100) NOT NULL,
    user_role character varying(50) NOT NULL,
    person_id integer NOT NULL
);
    DROP TABLE public.users;
       public         heap    postgres    false            �            1259    16534    users_id_seq    SEQUENCE     �   CREATE SEQUENCE public.users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.users_id_seq;
       public          postgres    false    220            C           0    0    users_id_seq    SEQUENCE OWNED BY     =   ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;
          public          postgres    false    218            �            1259    16535    users_personid_seq    SEQUENCE     �   CREATE SEQUENCE public.users_personid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.users_personid_seq;
       public          postgres    false    220            D           0    0    users_personid_seq    SEQUENCE OWNED BY     J   ALTER SEQUENCE public.users_personid_seq OWNED BY public.users.person_id;
          public          postgres    false    219            �           2604    16529 
   persons id    DEFAULT     h   ALTER TABLE ONLY public.persons ALTER COLUMN id SET DEFAULT nextval('public.persons_id_seq'::regclass);
 9   ALTER TABLE public.persons ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    217    216    217            �           2604    16567    products id    DEFAULT     j   ALTER TABLE ONLY public.products ALTER COLUMN id SET DEFAULT nextval('public.products_id_seq'::regclass);
 :   ALTER TABLE public.products ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    226    224    226            �           2604    16568    products sales_id    DEFAULT     u   ALTER TABLE ONLY public.products ALTER COLUMN sales_id SET DEFAULT nextval('public.products_salesid_seq'::regclass);
 @   ALTER TABLE public.products ALTER COLUMN sales_id DROP DEFAULT;
       public          postgres    false    226    225    226            �           2604    16552    sales id    DEFAULT     d   ALTER TABLE ONLY public.sales ALTER COLUMN id SET DEFAULT nextval('public.sales_id_seq'::regclass);
 7   ALTER TABLE public.sales ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    221    222    222            �           2604    16520 	   tokens id    DEFAULT     f   ALTER TABLE ONLY public.tokens ALTER COLUMN id SET DEFAULT nextval('public.tokens_id_seq'::regclass);
 8   ALTER TABLE public.tokens ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    215    214    215            �           2604    16539    users id    DEFAULT     d   ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);
 7   ALTER TABLE public.users ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    218    220    220            �           2604    16540    users person_id    DEFAULT     q   ALTER TABLE ONLY public.users ALTER COLUMN person_id SET DEFAULT nextval('public.users_personid_seq'::regclass);
 >   ALTER TABLE public.users ALTER COLUMN person_id DROP DEFAULT;
       public          postgres    false    219    220    220            3          0    16557 
   categories 
   TABLE DATA           .   COPY public.categories (cat_name) FROM stdin;
    public          postgres    false    223   �=       -          0    16526    persons 
   TABLE DATA           Z   COPY public.persons (id, first_name, last_name, email, address, phone_number) FROM stdin;
    public          postgres    false    217   �>       6          0    16564    products 
   TABLE DATA           k   COPY public.products (id, product_label, description, price, picture, sales_id, category_name) FROM stdin;
    public          postgres    false    226   �>       7          0    16584    roles 
   TABLE DATA           *   COPY public.roles (role_name) FROM stdin;
    public          postgres    false    227   �@       2          0    16549    sales 
   TABLE DATA           O   COPY public.sales (id, on_date, off_date, discount, products_list) FROM stdin;
    public          postgres    false    222   A       +          0    16517    tokens 
   TABLE DATA           I   COPY public.tokens (id, user_name_hash, validity, user_role) FROM stdin;
    public          postgres    false    215   �A       0          0    16536    users 
   TABLE DATA           Y   COPY public.users (id, user_name, password_hash, salt, user_role, person_id) FROM stdin;
    public          postgres    false    220   n[       E           0    0    persons_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.persons_id_seq', 2, true);
          public          postgres    false    216            F           0    0    products_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.products_id_seq', 68, true);
          public          postgres    false    224            G           0    0    products_salesid_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.products_salesid_seq', 23, true);
          public          postgres    false    225            H           0    0    sales_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.sales_id_seq', 11, true);
          public          postgres    false    221            I           0    0    tokens_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.tokens_id_seq', 130, true);
          public          postgres    false    214            J           0    0    users_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.users_id_seq', 2, true);
          public          postgres    false    218            K           0    0    users_personid_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.users_personid_seq', 1, false);
          public          postgres    false    219            �           2606    16561    categories categories_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.categories
    ADD CONSTRAINT categories_pkey PRIMARY KEY (cat_name);
 D   ALTER TABLE ONLY public.categories DROP CONSTRAINT categories_pkey;
       public            postgres    false    223            �           2606    16533    persons persons_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.persons
    ADD CONSTRAINT persons_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.persons DROP CONSTRAINT persons_pkey;
       public            postgres    false    217            �           2606    16572    products products_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.products
    ADD CONSTRAINT products_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.products DROP CONSTRAINT products_pkey;
       public            postgres    false    226            �           2606    16588    roles roles_pkey 
   CONSTRAINT     U   ALTER TABLE ONLY public.roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (role_name);
 :   ALTER TABLE ONLY public.roles DROP CONSTRAINT roles_pkey;
       public            postgres    false    227            �           2606    16556    sales sales_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.sales
    ADD CONSTRAINT sales_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.sales DROP CONSTRAINT sales_pkey;
       public            postgres    false    222            �           2606    16524    tokens tokens_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.tokens
    ADD CONSTRAINT tokens_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.tokens DROP CONSTRAINT tokens_pkey;
       public            postgres    false    215            �           2606    16542    users users_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.users DROP CONSTRAINT users_pkey;
       public            postgres    false    220            �           2606    16578    products fk_category    FK CONSTRAINT     �   ALTER TABLE ONLY public.products
    ADD CONSTRAINT fk_category FOREIGN KEY (category_name) REFERENCES public.categories(cat_name);
 >   ALTER TABLE ONLY public.products DROP CONSTRAINT fk_category;
       public          postgres    false    226    223    3219            �           2606    16543    users fk_person    FK CONSTRAINT     r   ALTER TABLE ONLY public.users
    ADD CONSTRAINT fk_person FOREIGN KEY (person_id) REFERENCES public.persons(id);
 9   ALTER TABLE ONLY public.users DROP CONSTRAINT fk_person;
       public          postgres    false    217    3213    220            �           2606    16589    users fk_role    FK CONSTRAINT     u   ALTER TABLE ONLY public.users
    ADD CONSTRAINT fk_role FOREIGN KEY (user_role) REFERENCES public.roles(role_name);
 7   ALTER TABLE ONLY public.users DROP CONSTRAINT fk_role;
       public          postgres    false    227    3223    220            �           2606    16573    products fk_sale    FK CONSTRAINT     p   ALTER TABLE ONLY public.products
    ADD CONSTRAINT fk_sale FOREIGN KEY (sales_id) REFERENCES public.sales(id);
 :   ALTER TABLE ONLY public.products DROP CONSTRAINT fk_sale;
       public          postgres    false    222    3217    226            3   �   x�M�A
�0E�s���C+�ŕ ��ā�Hf"zW���Sܸ��?�� J':������(�ʅUܶN�� ��m�o5 �1#��%�u���yp�-�/�>�E�sSS����W���p|�Y5C����^>�6>i���:�Wj���ʣ���5�[�����#�V�s[Fe:-���fX�      -   E   x�3������t)-��+���S�L�������"�? �2��MLO��L�t�O�̅�S�S����qqq ���      6   �  x����n�0�k�n��7[�I������5X����S�#/�`rD�t���w��<�і������XM�f	#���ɒ���j��{�IH�"���J�� �,�0��j����TScao���[�����`�A_n���J�N�є:ЯRf�M`ڲ����j�ʩ�(#|��xT����5૿oq�cI>v(<��t� ~�}ŋ�rc��,�wR�W�V�jX}��j?��Ytkjs��]��?1�(
?j<�D��Z��Iu��K:�~�����g����ڦm�N;�z��egn�$m�"H�Td2�A�(C�R|��>���X�&�̤ܡ1�Pi�꼉�+ӧ��it)����[��ro�Ɇ[��A?�}��[\�1�3Jv�$Xe��@N���6�(�T	"4E,�8�"e"���b���8eԇ��w�~��2��I����=�h�w �	=���1�`�ߠ��Ǽc�Ǹ���bE��/Y�$��1��      7      x�KL����*�,�,�/����� D��      2   p   x�}��� �I-��Ph�
����[vRQs�ab,H�b�S�'Y��'������[��U�uku
B�Ǹ&X%���6�\-)8�I��V�>lK!�-�睙oM7�      +      x���ْ;r��㼋Ұ�Áz]�֙�F��Gf�����4��`�5�����
��/ ���������_�����?��������˕B���?$�������?�Sޡ�|J�5j�ɎǦ�0f�UOh�k���C4�5INݿ�g
����B���j�������k֮Z$͒�>���0WL;�Y��;X�uX;2��ZGXb��Y$��߄�����a�2�V���R��ko���QR)K��򥝾f)�]��MX|'f=�0�+���/��b��a���đ�4-�턙bmd��G=�F<1�Y��\��
�v��}��"�A��S���Ev��V��-��6g8�N�Y��\����Z1�$y�I$M_�
��[��h����"-�L��W���r%Q+��*}�g�2�6�*��=��Q^9�J~.�^9̮+�>vȬ��\�F��[e��<�[M��S�hM,g�HNe���(�'�?´+�F�*g��ڤT-�Z�
?ҩA?�c�6ע���#e����$����)��ab��&j�qK��$��'�yV+2wk��؋<%I�U�I�\���g�
��P�� 1^�!Y��tr&s�$G��n�)S�He����ȇi���,����=�)Y�s?q�%M��6��MJ�6V���OQm�ot�'�UY�u����i��qr��JV[>���x&I���B��N��e1&ci��a����N�=Ęe�Hy>�df����G��,��@;�v����w5�<����ټ
 �C����c���o��>94t.�>p�V��Nce��C3��9g=4_E���CI�`�@a��8��o,&��S ��v�6�^;�|f���q�:��z��V�]w�����S�4�/~ı+�s[<�"/Zq�{�Lf_e�b��0�mp)J9�k��2衣���Xҫ4z�>c���i�c�0��-\����Г,O.إ&-wi�ۀA�+>�y˯D�T>8����ְ�w)�������¢F�1�"ռ��z�����aN�kk�7>H�*ƔP2�dJ�Y*|��`�K.��2��HZd0�I�D�?����W*J_P4�kRk�:'m%�S@��z'�M�ƺb���Z � 3IU�����@<9d	�N�jGo�9i�BTOȖ1����/���i�
��ÊH(�G×�������u�����K�A3����qñ��4A9`a�p(�6Gmq�3NdT_mZ��)l���E1��L��Nb@�Ƹ�Sj�h���و� K�=G��#�8�+�W� H�<���vӑ�g��W�{�`v�;�cz�j��f�I�q�z����0��4d�<)&�O��GW[�Y��t�)ļ��VI�dl�><�d��u2�t�,��\Y5�V�@�k4G	:�'��@}��h�|���jq�C��>���U���!�UH�:�v��I1�(�� p!+h/��D9O�!���q	���.H�e�<H����3:VsW9BG;1�Z!'�{����g�8���)u������)�1-Ӵ���H���304#�r�a���"	�r��߆���/�9@4�Qљ�b3㔙q�:���F���W�ی.E1���[�[}�W� F��AN�3Yȱ�x������np���n5��>�;a{Q#-I6@�Ku��+a��9_�TDd�� �~@g��q��ڶ�Q�Z�Ps�O�ƶO���g��%�-��C�U��^s�ed��z��a x��)��MS��@�1�P�YE��Gܘ�r[�^L�Zm3g4ܢ�Z��A�V���pDK��2q�I�Gdo�/,���'N�Nw�6)R��k��x��RM�3Rh4��-E�P��s�3Nv�q(y>q�
u�װ(i:�J�L�T��h׫-��0)�AÆ�G0LP��8����p�w�X!9z��R��l�t5���w=6@i��4��7QC`E��`��G#�&�[�ԓd�l<ڇg�ݘ�ћ���U`(�t@$�ɢb)<IA����|Pn��Ǔ"`�]��#���]��ߥ��^9�a�UϞJE.��1��z�3�4;�|�`&SA��q��0�����7 ��
�A��>�pL�)1���H��^�z#�>q�'GH,φt}p�|�`+���B��ܳ'�"� �M{	h{��'���Ң|�q��\��y��4gϵU"Oq��ʵ�8+z�����ݰ���zڗ"���fME.͡m�������,�5$G4��+�N� ����� �aN�A�y�����x��pG�/e����� �d� �3z�B[�M���L�Ε��q��ܹ���8��c�U�c��8q`	��FMbe��xx����Y`�-H4O|����P|+?��5��*�ۘ,x��� Mk֋�� ��>��!��~G��� �Av�A��bp
�K�6p�b}�����sy)�ƃX�3U�;I%�����S�D_��>`-�, �U��l�b�W���B�fE^���P�bU���c�3��^Jǟ�#��{� nf}�� �]�b���$���0�h��]Y�'���X>y�x������{�]����#W�@�d./�����k�����ۙRj��Ph���1bHb7�02C���^���Ⱥ[C~1�����-Ο;!��h���z: k�w���92��*��6d���^�Q����_���Y�H��`>C�rur���}.��U\\:i��<�; ��!�v������<⤷8��&g��q��n5"�	{ểX��q��˴�����U�<�> am:%�s=9}��B]���z P����3b�=�Y�g�J�!��@�,�E�3�ؖgqp>��z��}�����I����Bn$����2���S�����N&�yӷx�j�q���_h��
܂�ȓØ,^�S��EߢM4�vۚ���8��(�-o�b�P��9Ћ4��H�ۨ��׬~�ЕVWߥk��C+7Y��0F9o�#�D�]j��
<�W�������8Rbō�%�~�qT_8O^�C�%^dyh�Z�8��(*���sv�Z��\wǶJNT�f�*���j�2!�o�9q��#��Tp�䍥����l�|Ȗ�n��Q�v(Y��g��)Fo&�d�T&P ��m�'�Ai9�Z���o_C��-�;%=*E���7xA^���M���<��
�ɸ��'�q�4p/,(�8Mj���X2uc�KsS��n�n�(b4��'��=�4�ߋ��<�8���3�K�p�K����\��ͧR}��G�rmyHev�8M߂�7p��#Y���%:MXy�U�RqE!�3��A�M���b5�$vs@�3@����>JV�~{4?\A�96�X����v���m��S �<��s'�E�%*潏l�����
t�#((�_�0q0�Q Q���LK��W,cF��.����GI��`�[e�V���I�-c�]�:�$'��� �\	��<`c&�b�}�k uZxu$a���wu�j5�}��M��ͷ_�/�C�/��.���S����r������ ��v���j��$�m�3Nu����[�tE�s�6��3<ě��tG3�B 7T��C��aG2�?$�1x������M�������5�R:���bb
o6�t
/�Ԇ 0f�W�r��h��F��oR0>v��A�b�j�6wM���O��6[=OcX}�Vv��k��^U%��CY�^�i w@lrHV��u�}��O�bf"a�L�s��ݞA����֟�~ �n��h���q�+�#���@��j�����*ڐ�3:��=ARrz#[�� v1t�✅����p\�,fͤ,.��\9��uj~���Ѿk�w�\�;B�����g-��5�F�K%;��H�.�K2�Q�j�u���j�{s���/z�k�v�נ���L3�:��a  q�>��.���A�~�w��'�w�����>��"LI��#���-�ޠr���^|9�b�/�4 x1��q��q*0�a�{�Z�\��gL�R/w#ybﰄ��F��;0�b �	  K�N��8��ֺ��?�`h�n�5K�q�
�2� �x�h�5�8���+���ޘ���:�xj����/^�����sP;+��p�Fgfy�mpY8�Ď�o�²��]�?�-�rs�a#D��������;}�5�|��G��!��U+�)8CCwL�/��?� t�l�v`���HEs��qS���� LO`��8�b��@t9u�}9M�8�94��>�S�m�&�X���,�c@��_?�O�B@�ng�߶�Khy�g�껇~-#�GT���{� h����:��]�/�H{�K���m���i��0s�>�~g��e?{��^K I���lL"c�l[ =��^�#f�3K���$�Z��>q �F��X�t��E}���c���`K(��� N|����;���ƪ�	�H�����!���C��\:.�뎊�&bYq��q�Nь~ڍ��%�e�7Z�� ����͉��֘ ���O�A+���&���]�W�J�uoTH ��5�/q�7&rx��~����.��g�P�+��/E������]Vwt#�k��-��H1�F
��`��0{�����t�?#;�M����bGh��0w�~����g�]H��_i���}#��M�l�3�Qa9r��u W��Nh���3#+7m��q-�~ڃ�����a�IF뮺���X~J�|w��~�v�?�����6��zny+�d�Fq�}gV��S�0S�����B0t���l?3����G���_��/������m�}�<�H�˽#����1t#T�x���}���u�/+*�����[$v_Q�JC��=�4d��>	��t4��7��V�����
fl���s}����=�^���#Z��+[ڻ(�uRgb�֋#l86\1M�ߗ�r�"�qV���8�_�}����Ё�S�Q��@D0VXA�E m,�����-��b����_C�Д|kŀ�ޯ�۸��a�	���P���B�B+��F;�g �^�V��ZPH�t[�w�W!�EB�h�i|����ڡ�%���H$����@�&�d�_�k�5*J���x�j1����T��Ǐ�T��?�q� �V|O�(����1�m���5ԅ"Ic�G�g���(�B*��3�}V�	��[*f��o*�KB���o���n�H�SdBޱ(!֛�v��t!���V`��O�RJ����Ö߮�rU<'�Ѵ��o��_~t/�����iz
��G�����C�3������uvA2(نgD�,�}�6�f�X�7t�)L�G���~�dv˘@^�H�WFa��t���^�8�fXh���gs���t���ήX�r����pvN}�~+��60��@Ԓ�m�K����y_~+7�3���u0[1�-!�үŜ��&�U�]w��Z������3�p���u�8i���j��527n%�~2���(�n/%�v;2�1b����nm$v��;�� s+f�bF�u-����MG�4(�����i���(�*3�jݛ���s%�N������3�2"2M�(n4����A�F���r�[��D� ��1���I��CT��FU*
�fД�gl��E�z��,8��{�enb;F��7��`v|3
�T���q��'J��~MS��0�)����J��5�|�������d��@�¦��/�t�{C#VXӏ�*�A����} $}�{'�K�|���0�>��wU��\��t��'�%t�)�O���o���x�h�*��k�J~��8qM��"m&�����YW��H��l�����y�XP�~�c��BF	n�B�}��;���eu7�[zB������g�	�_Q����(���)��F-=k��In��c�K��&�e�T$pH�.����C'�zC9L��Z���IX�"�����˝��A̯�b>*���)��c^{z�~��XW�c�_N��s��/�5�<Ģ�`�}�^iA���w�2M�r��V�|E|�p���Z(`DCÑ3T:�u(X�t���Dm��g^~��T�톚��(��'L���[ <D��q��b�}ibT�\�Uo��T˪�3�-CNSCd��R������v���~"X�|$��6"Y�<7�ĝ%��w��_cFPtL��gL�J4��rx	�@�����������	ك�'�뷘���G7a��7��<��q���	���`���T��
����}
�����{
�w�;Vz�6�oe'��.��@�����Wd��BS��>~;�k�sj~���xQ�AA��(�`�f�6���_WT���)}��|n�E�oZ�L���2�ӏ:�W~�����@����$��(�*��$�`̵���1�
e\�_�Иȉ�q�����a��i}%� �&|���T�(t�~ݽaa����]>KC�,��<9��p)'�BI��yj�>����.p��������)�      0     x�E��N�@ �u�1d��3;��Th(+$nng��!�	�z7&�/8<�c;��)���;��w����h2�2���֊+�w,8`�H(��[@�mf���P	��K7u?m�X�>��K�5,�4��qS_�����9.���ڧ�[���1�Hl�ؑ����J9b�e��2ޓ� u�A	i5���@�S^Y�9�S9q�ܝ�fWem�x^�]�%o���j;�jh����r{*��n��zu�����s���/c     