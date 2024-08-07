----------------------------------------------
CREATE TABLE IF NOT EXISTS public.addresses
(
    address_id bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    city character varying(255) COLLATE pg_catalog."default",
    district character varying(255) COLLATE pg_catalog."default",
    street character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT addresses_pkey PRIMARY KEY (address_id)
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.addresses
    OWNER to postgres;

----------------------------------------------
CREATE TABLE IF NOT EXISTS public.categories
(
    category_id bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    category_name character varying(255) COLLATE pg_catalog."default",
    category_status character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT categories_pkey PRIMARY KEY (category_id),
    CONSTRAINT categories_category_status_check CHECK (category_status::text = ANY (ARRAY['ACTIVE'::character varying, 'INACTIVE'::character varying]::text[]))
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.categories
    OWNER to postgres;

----------------------------------------------

CREATE TABLE IF NOT EXISTS public.colors
(
    color_id bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    color_name character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT colors_pkey PRIMARY KEY (color_id)
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.colors
    OWNER to postgres;

----------------------------------------------
CREATE TABLE IF NOT EXISTS public.comment_media
(
    media_id bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    media_type character varying(255) COLLATE pg_catalog."default",
    path character varying(255) COLLATE pg_catalog."default",
    comment_id bigint NOT NULL,
    CONSTRAINT comment_media_pkey PRIMARY KEY (media_id),
    CONSTRAINT fkt8wo02jsyvj694xbvndwoknkp FOREIGN KEY (comment_id)
    REFERENCES public.comments (comment_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION,
    CONSTRAINT comment_media_media_type_check CHECK (media_type::text = ANY (ARRAY['IMAGE'::character varying, 'VIDEO'::character varying]::text[]))
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.comment_media
    OWNER to postgres;

----------------------------------------------

CREATE TABLE IF NOT EXISTS public.comments
(
    comment_id bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    created_at timestamp(6) without time zone,
    updated_at timestamp(6) without time zone,
    comment_date timestamp(6) without time zone,
    rating integer,
    text_content character varying(255) COLLATE pg_catalog."default",
    product_id bigint NOT NULL,
    user_id bigint NOT NULL,
    CONSTRAINT comments_pkey PRIMARY KEY (comment_id),
    CONSTRAINT fk6uv0qku8gsu6x1r2jkrtqwjtn FOREIGN KEY (product_id)
    REFERENCES public.products (product_id) MATCH SIMPLE
                            ON UPDATE NO ACTION
                            ON DELETE NO ACTION,
    CONSTRAINT fk8omq0tc18jd43bu5tjh6jvraq FOREIGN KEY (user_id)
    REFERENCES public.users (user_id) MATCH SIMPLE
                            ON UPDATE NO ACTION
                            ON DELETE NO ACTION
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.comments
    OWNER to postgres;

----------------------------------------------
CREATE TABLE IF NOT EXISTS public.message_media
(
    media_id bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    media_type character varying(255) COLLATE pg_catalog."default",
    path character varying(255) COLLATE pg_catalog."default",
    message_id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT message_media_pkey PRIMARY KEY (media_id),
    CONSTRAINT fk9dofr3ed1b29u3g5rkf4bgypi FOREIGN KEY (message_id)
    REFERENCES public.messages (message_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION,
    CONSTRAINT message_media_media_type_check CHECK (media_type::text = ANY (ARRAY['IMAGE'::character varying, 'VIDEO'::character varying]::text[]))
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.message_media
    OWNER to postgres;

----------------------------------------------

CREATE TABLE IF NOT EXISTS public.messages
(
    message_id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    message_type character varying(255) COLLATE pg_catalog."default",
    receiver character varying(255) COLLATE pg_catalog."default" NOT NULL,
    send_date timestamp(6) without time zone,
    sender character varying(255) COLLATE pg_catalog."default" NOT NULL,
    room_id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT messages_pkey PRIMARY KEY (message_id),
    CONSTRAINT fk2st1837tmjdy0qx4vswxs1xjp FOREIGN KEY (room_id)
    REFERENCES public.room_chat (room_id) MATCH SIMPLE
                           ON UPDATE NO ACTION
                           ON DELETE NO ACTION,
    CONSTRAINT messages_message_type_check CHECK (message_type::text = ANY (ARRAY['TEXT'::character varying, 'IMAGE'::character varying, 'VIDEO'::character varying]::text[]))
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.messages
    OWNER to postgres;

----------------------------------------------

CREATE TABLE IF NOT EXISTS public.notification_read
(
    notification_id bigint NOT NULL,
    user_id bigint NOT NULL,
    CONSTRAINT notification_read_pkey PRIMARY KEY (notification_id, user_id),
    CONSTRAINT fkebhxa2jan0mu9air2lhwdl5qe FOREIGN KEY (notification_id)
    REFERENCES public.notifications (notification_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION,
    CONSTRAINT fkhggqhegtph0109s705vjpfqn FOREIGN KEY (user_id)
    REFERENCES public.users (user_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.notification_read
    OWNER to postgres;

----------------------------------------------
CREATE TABLE IF NOT EXISTS public.notifications
(
    notification_id bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    content character varying(255) COLLATE pg_catalog."default",
    notification_date timestamp(6) without time zone,
    scope character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT notifications_pkey PRIMARY KEY (notification_id),
    CONSTRAINT notifications_scope_check CHECK (scope::text = ANY (ARRAY['ALL'::character varying, 'FOR_USER'::character varying]::text[]))
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.notifications
    OWNER to postgres;

----------------------------------------------
CREATE TABLE IF NOT EXISTS public.order_datails
(
    amount double precision,
    quantity integer,
    product_detail_id bigint NOT NULL,
    order_id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT order_datails_pkey PRIMARY KEY (order_id, product_detail_id),
    CONSTRAINT fkp01nx10f42gb5bplch31bwakb FOREIGN KEY (product_detail_id)
    REFERENCES public.product_details (product_detail_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION,
    CONSTRAINT fkplp84hx96mk49qnuugvh6sggo FOREIGN KEY (order_id)
    REFERENCES public.orders (order_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.order_datails
    OWNER to postgres;

----------------------------------------------

CREATE TABLE IF NOT EXISTS public.order_voucher
(
    order_id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    voucher_id bigint NOT NULL,
    CONSTRAINT order_voucher_pkey PRIMARY KEY (order_id, voucher_id),
    CONSTRAINT fksrhuwor1f2tjthp6k4tmuwrqa FOREIGN KEY (order_id)
    REFERENCES public.orders (order_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION,
    CONSTRAINT fksvm2d55bceb27bgd3t27co8ge FOREIGN KEY (voucher_id)
    REFERENCES public.voucher (voucher_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.order_voucher
    OWNER to postgres;

----------------------------------------------

CREATE TABLE IF NOT EXISTS public.orders
(
    order_id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    created_at timestamp(6) without time zone,
    updated_at timestamp(6) without time zone,
    byer_name character varying(255) COLLATE pg_catalog."default",
    delivery_amount double precision,
    delivery_method character varying(255) COLLATE pg_catalog."default",
    discounted_amount double precision,
    discounted_price double precision,
    note character varying(255) COLLATE pg_catalog."default",
    order_date timestamp(6) without time zone,
    original_amount double precision,
    payment_method character varying(255) COLLATE pg_catalog."default",
    phone_number character varying(255) COLLATE pg_catalog."default",
    status character varying(255) COLLATE pg_catalog."default",
    address_id bigint NOT NULL,
    user_id bigint NOT NULL,
    CONSTRAINT orders_pkey PRIMARY KEY (order_id),
    CONSTRAINT ukhlnl243wmdrj3u8nrxw1qs9yt UNIQUE (address_id),
    CONSTRAINT fk32ql8ubntj5uh44ph9659tiih FOREIGN KEY (user_id)
    REFERENCES public.users (user_id) MATCH SIMPLE
                            ON UPDATE NO ACTION
                            ON DELETE NO ACTION,
    CONSTRAINT fkhlglkvf5i60dv6dn397ethgpt FOREIGN KEY (address_id)
    REFERENCES public.addresses (address_id) MATCH SIMPLE
                            ON UPDATE NO ACTION
                            ON DELETE NO ACTION,
    CONSTRAINT orders_delivery_method_check CHECK (delivery_method::text = ANY (ARRAY['EXPRESS'::character varying, 'ECONOMY'::character varying]::text[])),
    CONSTRAINT orders_payment_method_check CHECK (payment_method::text = ANY (ARRAY['COD'::character varying, 'CC'::character varying]::text[])),
    CONSTRAINT orders_status_check CHECK (status::text = ANY (ARRAY['PENDING'::character varying, 'PROCESSING'::character varying, 'SHIPPED'::character varying, 'DELIVERED'::character varying, 'CANCELLED'::character varying]::text[]))
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.orders
    OWNER to postgres;

----------------------------------------------
CREATE TABLE IF NOT EXISTS public.product_details
(
    product_detail_id bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    quantity integer,
    color_id bigint NOT NULL,
    product_id bigint NOT NULL,
    size_id bigint NOT NULL,
    CONSTRAINT product_details_pkey PRIMARY KEY (product_detail_id),
    CONSTRAINT fk5tcr8s5m0i3prlty9w0crocog FOREIGN KEY (color_id)
    REFERENCES public.colors (color_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION,
    CONSTRAINT fkccs28awcbxp3s7mjthrwcdwvn FOREIGN KEY (size_id)
    REFERENCES public.sizes (size_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION,
    CONSTRAINT fknfvvq3meg4ha3u1bju9k4is3r FOREIGN KEY (product_id)
    REFERENCES public.products (product_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.product_details
    OWNER to postgres;

----------------------------------------------
CREATE TABLE IF NOT EXISTS public.product_images
(
    product_image_id bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    path character varying(255) COLLATE pg_catalog."default",
    product_id bigint NOT NULL,
    CONSTRAINT product_images_pkey PRIMARY KEY (product_image_id),
    CONSTRAINT fkqnq71xsohugpqwf3c9gxmsuy FOREIGN KEY (product_id)
    REFERENCES public.products (product_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.product_images
    OWNER to postgres;

----------------------------------------------
CREATE TABLE IF NOT EXISTS public.product_prices
(
    product_price_id bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    discount real,
    discounted_amount numeric(10,2),
    discounted_price double precision,
    expired_date timestamp(6) without time zone,
    note character varying(255) COLLATE pg_catalog."default",
    product_id bigint NOT NULL,
    CONSTRAINT product_prices_pkey PRIMARY KEY (product_price_id),
    CONSTRAINT fko21ew0lemtpkoyly3vm1mq925 FOREIGN KEY (product_id)
    REFERENCES public.products (product_id) MATCH SIMPLE
                              ON UPDATE NO ACTION
                              ON DELETE NO ACTION
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.product_prices
    OWNER to postgres;

----------------------------------------------
CREATE TABLE IF NOT EXISTS public.products
(
    product_id bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    created_at timestamp(6) without time zone,
    updated_at timestamp(6) without time zone,
    avg_rating numeric(10,1),
    buy_quantity integer,
    description character varying(255) COLLATE pg_catalog."default",
    number_of_rating integer,
    price numeric(10,2),
    product_name character varying(255) COLLATE pg_catalog."default",
    product_status character varying(255) COLLATE pg_catalog."default",
    thumbnail character varying(255) COLLATE pg_catalog."default",
    total_quantity integer,
    category_id bigint NOT NULL,
    provider_id bigint NOT NULL,
    CONSTRAINT products_pkey PRIMARY KEY (product_id),
    CONSTRAINT fkog2rp4qthbtt2lfyhfo32lsw9 FOREIGN KEY (category_id)
    REFERENCES public.categories (category_id) MATCH SIMPLE
                            ON UPDATE NO ACTION
                            ON DELETE NO ACTION,
    CONSTRAINT fktltawi3myjt9pi09219eiou1o FOREIGN KEY (provider_id)
    REFERENCES public.providers (provider_id) MATCH SIMPLE
                            ON UPDATE NO ACTION
                            ON DELETE NO ACTION,
    CONSTRAINT products_product_status_check CHECK (product_status::text = ANY (ARRAY['ACTIVE'::character varying, 'INACTIVE'::character varying]::text[]))
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.products
    OWNER to postgres;

----------------------------------------------
CREATE TABLE IF NOT EXISTS public.providers
(
    provider_id bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    email character varying(255) COLLATE pg_catalog."default",
    phone_number character varying(255) COLLATE pg_catalog."default",
    provider_name character varying(255) COLLATE pg_catalog."default",
    provider_status character varying(255) COLLATE pg_catalog."default" NOT NULL,
    address_id bigint NOT NULL,
    CONSTRAINT providers_pkey PRIMARY KEY (provider_id),
    CONSTRAINT uksa9qdjnttohyay676npbf85kg UNIQUE (address_id),
    CONSTRAINT fkipcqyoewj5y5mkrbmdm8d9cf9 FOREIGN KEY (address_id)
    REFERENCES public.addresses (address_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION,
    CONSTRAINT providers_provider_status_check CHECK (provider_status::text = ANY (ARRAY['ACTIVE'::character varying, 'INACTIVE'::character varying]::text[]))
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.providers
    OWNER to postgres;

----------------------------------------------
CREATE TABLE IF NOT EXISTS public.room_chat
(
    room_id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    is_seen boolean,
    user_id bigint NOT NULL,
    CONSTRAINT room_chat_pkey PRIMARY KEY (room_id),
    CONSTRAINT fkkjcs9j03jhexcrp7xv1a88mpb FOREIGN KEY (user_id)
    REFERENCES public.users (user_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.room_chat
    OWNER to postgres;

----------------------------------------------

CREATE TABLE IF NOT EXISTS public.sizes
(
    size_id bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    number_size integer,
    size_type character varying(255) COLLATE pg_catalog."default",
    text_size character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT sizes_pkey PRIMARY KEY (size_id),
    CONSTRAINT sizes_size_type_check CHECK (size_type::text = ANY (ARRAY['NUMBER'::character varying, 'TEXT'::character varying]::text[]))
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.sizes
    OWNER to postgres;

----------------------------------------------

CREATE TABLE IF NOT EXISTS public.tokens
(
    token_id bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    access_token character varying(255) COLLATE pg_catalog."default",
    expired_date timestamp(6) without time zone,
    refresh_token character varying(255) COLLATE pg_catalog."default",
    user_id bigint NOT NULL,
    CONSTRAINT tokens_pkey PRIMARY KEY (token_id),
    CONSTRAINT fk2dylsfo39lgjyqml2tbe0b0ss FOREIGN KEY (user_id)
    REFERENCES public.users (user_id) MATCH SIMPLE
                              ON UPDATE NO ACTION
                              ON DELETE NO ACTION
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.tokens
    OWNER to postgres;

----------------------------------------------

CREATE TABLE IF NOT EXISTS public.user_notifications
(
    notification_id bigint NOT NULL,
    user_id bigint NOT NULL,
    CONSTRAINT user_notifications_pkey PRIMARY KEY (notification_id, user_id),
    CONSTRAINT fk9f86wonnl11hos1cuf5fibutl FOREIGN KEY (user_id)
    REFERENCES public.users (user_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION,
    CONSTRAINT fkovvx0ab3h8s9lrm6fppuadn7d FOREIGN KEY (notification_id)
    REFERENCES public.notifications (notification_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.user_notifications
    OWNER to postgres;

----------------------------------------------
CREATE TABLE IF NOT EXISTS public.user_voucher
(
    is_used boolean,
    user_id bigint NOT NULL,
    voucher_id bigint NOT NULL,
    CONSTRAINT user_voucher_pkey PRIMARY KEY (user_id, voucher_id),
    CONSTRAINT fk5llb4x2ixiwa75csgei7hbl5r FOREIGN KEY (voucher_id)
    REFERENCES public.voucher (voucher_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION,
    CONSTRAINT fkqbre9r4sb85kdd0q1d9fa8c9m FOREIGN KEY (user_id)
    REFERENCES public.users (user_id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.user_voucher
    OWNER to postgres;

----------------------------------------------
CREATE TABLE IF NOT EXISTS public.users
(
    user_id bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    created_at timestamp(6) without time zone,
    updated_at timestamp(6) without time zone,
    date_of_birth timestamp(6) without time zone,
    email character varying(255) COLLATE pg_catalog."default",
    gender character varying(255) COLLATE pg_catalog."default",
    name character varying(255) COLLATE pg_catalog."default",
    password character varying(255) COLLATE pg_catalog."default",
    phone_number character varying(255) COLLATE pg_catalog."default",
    role character varying(255) COLLATE pg_catalog."default",
    address_id bigint NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (user_id),
    CONSTRAINT uk6dotkott2kjsp8vw4d0m25fb7 UNIQUE (email),
    CONSTRAINT ukhbvhqvjgmhd5omxyo67ynvbyp UNIQUE (address_id),
    CONSTRAINT fke8vydtk7hf0y16bfm558sywbb FOREIGN KEY (address_id)
    REFERENCES public.addresses (address_id) MATCH SIMPLE
                            ON UPDATE NO ACTION
                            ON DELETE NO ACTION,
    CONSTRAINT users_gender_check CHECK (gender::text = ANY (ARRAY['MALE'::character varying, 'FEMALE'::character varying]::text[])),
    CONSTRAINT users_role_check CHECK (role::text = ANY (ARRAY['USER'::character varying, 'ADMIN'::character varying]::text[]))
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.users
    OWNER to postgres;

----------------------------------------------
CREATE TABLE IF NOT EXISTS public.voucher
(
    voucher_id bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    discount double precision,
    expried_date timestamp(6) without time zone,
    max_price double precision,
    min_amount double precision,
    quantity integer,
    scope character varying(255) COLLATE pg_catalog."default",
    voucher_type character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT voucher_pkey PRIMARY KEY (voucher_id),
    CONSTRAINT voucher_scope_check CHECK (scope::text = ANY (ARRAY['ALL'::character varying, 'FOR_USER'::character varying]::text[])),
    CONSTRAINT voucher_voucher_type_check CHECK (voucher_type::text = ANY (ARRAY['FOR_PRODUCT'::character varying, 'FOR_DELIVERY'::character varying]::text[]))
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.voucher
    OWNER to postgres;

----------------------------------------------

CREATE TABLE IF NOT EXISTS public.voucher_usages
(
    usages_date timestamp(6) without time zone,
    user_id bigint NOT NULL,
    voucher_id bigint NOT NULL,
    CONSTRAINT voucher_usages_pkey PRIMARY KEY (user_id, voucher_id),
    CONSTRAINT fk1xrihdxx7m2ko1txe5hjdpsom FOREIGN KEY (voucher_id)
    REFERENCES public.voucher (voucher_id) MATCH SIMPLE
                             ON UPDATE NO ACTION
                             ON DELETE NO ACTION,
    CONSTRAINT fkayocv8v53p94c3jr2ookjd4jq FOREIGN KEY (user_id)
    REFERENCES public.users (user_id) MATCH SIMPLE
                             ON UPDATE NO ACTION
                             ON DELETE NO ACTION
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.voucher_usages
    OWNER to postgres;