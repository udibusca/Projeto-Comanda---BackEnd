CREATE TABLE web_adicional_produto (
  id bigint(20) NOT NULL,
  id_produto bigint(20) NOT NULL,
  id_adicional bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

INSERT INTO web_adicional_produto (id, id_produto, id_adicional) VALUES
(1, 8, 5),
(2, 8, 8),
(3, 8, 10),
(4, 8, 10),
(5, 8, 11),
(6, 8, 13),
(7, 9, 5),
(8, 9, 8),
(9, 9, 9),
(10, 9, 10),
(11, 9, 11),
(12, 9, 13),
(13, 10, 5),
(14, 10, 8),
(15, 10, 9),
(16, 10, 10),
(17, 10, 11),
(18, 10, 13),
(19, 11, 5),
(20, 11, 8),
(21, 11, 9),
(22, 11, 10),
(23, 11, 11),
(24, 11, 13),
(25, 12, 5),
(26, 12, 8),
(27, 12, 9),
(28, 12, 10),
(29, 12, 11),
(30, 12, 13),
(31, 13, 4),
(32, 13, 13),
(33, 14, 4),
(34, 14, 13);

ALTER TABLE web_adicional_produto
  ADD PRIMARY KEY (id),
  ADD KEY fk_adicional (id_adicional),
  ADD KEY fk_produto (id_produto);

ALTER TABLE web_adicional_produto
  MODIFY id bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

ALTER TABLE web_adicional_produto
  ADD CONSTRAINT fk_adicional FOREIGN KEY (id_adicional) REFERENCES web_adicional (id),
  ADD CONSTRAINT fk_produto FOREIGN KEY (id_produto) REFERENCES web_produto (id);
COMMIT;