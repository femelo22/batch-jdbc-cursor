package com.springbatch.jdbccursorreader.reader;

import javax.sql.DataSource;

import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.springbatch.jdbccursorreader.dominio.Cliente;

@Configuration
public class JdbcCursorReaderConfig {
	
	
	/**
	 * JdbcCursorItemReader - Componente do spring batch que lê com cursor
	 * @Qualifier("app.datasource") - Ejatar o DataSource que criamos dentro do nosso leitor
	 * 	rowMapper - Ajuda a trasformar o resultset que recebemos do banco em um objeto de dominio (Cliente)
	 * 				Ele já faz o parse dos resultados para nossa classe, se as propriedades tiverem o mesmo nome.
	 */
	
	@Bean
	public JdbcCursorItemReader<Cliente> jdbcCursorReader(@Qualifier("app.datasource") DataSource dataSource) {
		return new JdbcCursorItemReaderBuilder<Cliente>()
				.name("jdbcCursorReader")
				.dataSource(dataSource)
				.sql("select * from cliente")
				.rowMapper(new BeanPropertyRowMapper<Cliente>(Cliente.class))
				.build();
	}
}
