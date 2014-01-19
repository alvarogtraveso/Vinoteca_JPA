package ca.vinote.config;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import ca.vinote.dao.BodegaDao;
import ca.vinote.dao.DatosUsuarioDao;
import ca.vinote.dao.DetallePedidoDao;
import ca.vinote.dao.IBodegaDao;
import ca.vinote.dao.IDatosUsuarioDao;
import ca.vinote.dao.IDetallePedidoDao;
import ca.vinote.dao.IPedidoDao;
import ca.vinote.dao.IUsuarioDao;
import ca.vinote.dao.IVinoDao;
import ca.vinote.dao.PedidoDao;
import ca.vinote.dao.TransactionProxy;
import ca.vinote.dao.TransactionProxyFactory;
import ca.vinote.dao.UsuarioDao;
import ca.vinote.dao.VinoDao;
import ca.vinote.service.BodegaServiceImpl;
import ca.vinote.service.IBodegaService;
import ca.vinote.service.IPedidoService;
import ca.vinote.service.IUsuarioService;
import ca.vinote.service.IVinoService;
import ca.vinote.service.PedidoServiceImpl;
import ca.vinote.service.UsuarioServiceImpl;
import ca.vinote.service.VinoServiceImpl;

@Configuration
public class Config {
	
	@Bean(initMethod="init")
	public IBodegaDao bodegaDao() {
		BodegaDao bodegaDao = new BodegaDao(false);
		bodegaDao.setEntityManagerFactory(entityManagerFactory());
		return bodegaDao;
	}
	
	@Bean(initMethod="init")
	public IDetallePedidoDao detallePedidoDao() {
		DetallePedidoDao detallePedidoDao = new DetallePedidoDao(false);
		detallePedidoDao.setEntityManagerFactory(entityManagerFactory());
		return detallePedidoDao;
	}
	
	@Bean(initMethod="init")
	public IDatosUsuarioDao datosUsuarioDao() {
		DatosUsuarioDao datosUsuarioDao = new DatosUsuarioDao(false);
		datosUsuarioDao.setEntityManagerFactory(entityManagerFactory());
		return datosUsuarioDao;
	}
	
	@Bean(initMethod="init")
	public IUsuarioDao usuarioDao() {
		UsuarioDao usuarioDao = new UsuarioDao(false);
		usuarioDao.setEntityManagerFactory(entityManagerFactory());
		return usuarioDao;
	}
			
	@Bean(initMethod="init")
	public IPedidoDao pedidoDao() {
		PedidoDao pedidoDao = new PedidoDao(false);
		pedidoDao.setEntityManagerFactory(entityManagerFactory());
		return pedidoDao;
	}
		
	@Bean(initMethod="init")
	@Scope("prototype")
	public IVinoDao vinoDao() {
		VinoDao vinoDao = new VinoDao(false);
		vinoDao.setEntityManagerFactory(entityManagerFactory());
		return vinoDao;
	}
	
	
	@Bean
	public IBodegaService bodegaService() {
		return new BodegaServiceImpl();
	}
	
	@Bean
	public IUsuarioService usuarioService() {
		return new UsuarioServiceImpl();
	}
	
	@Bean
	public IPedidoService pedidoService() {
		return new PedidoServiceImpl();
	}
	
	@Bean
	public IVinoService vinoService() {
		return new VinoServiceImpl();
	}
	
	@Bean
	public EntityManagerFactory entityManagerFactory() {
		return Persistence.createEntityManagerFactory("Vinoteca_JPA");
	}

	@Bean
	public TransactionProxyFactory transactionProxyFactory() {
		return new TransactionProxyFactory();
	}
	
	@Bean(initMethod="init")
	@Scope("prototype")
	public TransactionProxy transactionProxy() {
		return new TransactionProxy();
	}
	
}
