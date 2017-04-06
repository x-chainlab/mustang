package com.dimogo.open.myjobs.loader;

import com.dimogo.open.myjobs.servlet.ApplicationContextCatcher;
import org.springframework.batch.core.configuration.support.ApplicationContextFactory;
import org.springframework.batch.core.configuration.support.ClasspathXmlApplicationContextsFactoryBean;
import org.springframework.batch.core.configuration.support.GenericApplicationContextFactory;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.core.io.UrlResource;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created by Ethan Xiao on 2017/4/1.
 */
public class ExternalJarLoader {
	private static String jarPath = "/Users/xuechengguyue/OpenSource/mustang/hello-jar/target/hello-jar-1.0-jar-with-dependencies.jar";

	//		URL jarPath = new URL("file:/Users/xuechengguyue/OpenSource/mustang/hello-jar/target/hello-jar-1.0.jar");
//		URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{jarPath}, Thread.currentThread().getContextClassLoader());
//		Object obj = urlClassLoader.loadClass("com.dimogo.open.myjobs.test.HelloJar").newInstance();
//		System.out.println(obj instanceof Tasklet);

//		ExternalJarLoader.load();

	public static void load() {
		try {
			ApplicationContext applicationContext = ApplicationContextCatcher.getInstance().get();
//			DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) applicationContext.getAutowireCapableBeanFactory();

			String configurationFilePath = "jar:file:" + jarPath + "!/myjobs/hello-jar.xml";

//			URL url = new URL(configurationFilePath);
//
//			UrlResource urlResource = new UrlResource(url);
//
//			XmlBeanFactory xbf = new XmlBeanFactory(urlResource);
//
//
//			String[] beanIds = xbf.getBeanDefinitionNames();
//
//			for (String beanId : beanIds) {
//				BeanDefinition bd = xbf.getMergedBeanDefinition(beanId);
//				beanFactory.registerBeanDefinition(beanId, bd);
//			}

			// 以下这行设置BeanFactory的ClassLoader，以加载外部类
//			setBeanClassLoader(beanFactory);

//			ApplicationContextFactory factory = applicationContext.getBean("xmlJobContextFactories", ApplicationContextFactory.class);
//			GenericApplicationContextFactory factory2 = (GenericApplicationContextFactory)factory;
//			System.out.println(factory2.getClass().getName());

//			Object pluginBean = applicationContext.getBean("helloJar");
//			tryInvoke(pluginBean);

			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(applicationContext);
			setBeanClassLoader(context);
			context.setAllowBeanDefinitionOverriding(false);
			context.setConfigLocation(configurationFilePath);
			context.refresh();
			Object pluginBean = context.getBean("helloJar");
			tryInvoke(pluginBean);

		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	private static void setBeanClassLoader(ClassPathXmlApplicationContext beanFactory) throws MalformedURLException {
		URL jarUrl = new File(jarPath).toURI().toURL();
		URL[] urls = new URL[]{jarUrl};
		URLClassLoader cl = new URLClassLoader(urls);
		beanFactory.setClassLoader(cl);
	}

	private static void setBeanClassLoader(DefaultListableBeanFactory beanFactory) throws MalformedURLException {
		URL jarUrl = new File(jarPath).toURI().toURL();
		URL[] urls = new URL[]{jarUrl};
		URLClassLoader cl = new URLClassLoader(urls);
		beanFactory.setBeanClassLoader(cl);
	}

	private static void tryInvoke(Object bean) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		System.out.println(bean.getClass().getName());
		System.out.println(bean instanceof Tasklet);
		for (Class<?> c : bean.getClass().getClasses()) {
			System.out.println("1===" + c.getName());
		}
		for (Class<?> c : bean.getClass().getDeclaredClasses()) {
			System.out.println("2===" + c.getName());
		}
	}
}
