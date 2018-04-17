CREATE TABLE web_item_produto_adicional (
  id bigint(20) NOT NULL,
  item_id_produto bigint(20) NOT NULL,
  id_adicional bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

ALTER TABLE web_item_produto_adicional
  ADD PRIMARY KEY (id),
  ADD KEY fk_adicional_item (id_adicional),
  ADD KEY fk_item_id_produto (item_id_produto);

ALTER TABLE web_item_produto_adicional
  MODIFY id bigint(20) NOT NULL AUTO_INCREMENT;

ALTER TABLE web_item_produto_adicional
  ADD CONSTRAINT fk_adicional_item FOREIGN KEY (id_adicional) REFERENCES web_adicional (id),
  ADD CONSTRAINT fk_item_id_produto FOREIGN KEY (item_id_produto) REFERENCES web_item_pedido (id_produto);
COMMIT;
