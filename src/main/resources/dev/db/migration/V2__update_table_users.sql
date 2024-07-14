ALTER TABLE users
ADD verify boolean,
ADD otp varchar(6);

ALTER TABLE users DROP CONSTRAINT users_role_check;

UPDATE users
SET role = CASE
               WHEN role = 'USER' THEN 'ROLE_USER'
               WHEN role = 'ADMIN' THEN 'ROLE_ADMIN'
               ELSE role
    END;
ALTER TABLE users
    ADD CONSTRAINT users_role_check
        CHECK (role::text = ANY (ARRAY['ROLE_USER'::character varying, 'ROLE_ADMIN'::character varying]::text[]));