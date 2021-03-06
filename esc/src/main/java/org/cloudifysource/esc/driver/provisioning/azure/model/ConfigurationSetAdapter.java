/**
 * 
 */
package org.cloudifysource.esc.driver.provisioning.azure.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * @author elip
 * 
 */
public class ConfigurationSetAdapter
		extends
		XmlAdapter<ConfigurationSetAdapter.AdaptedConfigurationSet, ConfigurationSet> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.xml.bind.annotation.adapters.XmlAdapter#unmarshal(java.lang.Object)
	 */
	@Override
	public ConfigurationSet unmarshal(
			final AdaptedConfigurationSet adaptedConfigurationSet)
			throws Exception {
		if (adaptedConfigurationSet == null) {
			return null;
		}

		if (adaptedConfigurationSet.inputEndpoints != null) {
			NetworkConfigurationSet networkConfigurationSet = new NetworkConfigurationSet();
			networkConfigurationSet
					.setConfigurationSetType(adaptedConfigurationSet.configurationSetType);
			networkConfigurationSet
					.setInputEndpoints(adaptedConfigurationSet.inputEndpoints);
			return networkConfigurationSet;
		} else {
			LinuxProvisioningConfigurationSet linuxProvisioningConfigurationSet = 
					new LinuxProvisioningConfigurationSet();
			linuxProvisioningConfigurationSet
					.setDisableSshPasswordAuthentication(adaptedConfigurationSet.disableSshPasswordAuthentication);
			linuxProvisioningConfigurationSet
					.setHostName(adaptedConfigurationSet.hostName);
			linuxProvisioningConfigurationSet
					.setUserName(adaptedConfigurationSet.userName);
			linuxProvisioningConfigurationSet
					.setUserPassword(adaptedConfigurationSet.userPassword);
		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.xml.bind.annotation.adapters.XmlAdapter#marshal(java.lang.Object)
	 */
	@Override
	public AdaptedConfigurationSet marshal(final ConfigurationSet configurationSet)
			throws Exception {

		if (configurationSet == null) {
			return null;
		}

		AdaptedConfigurationSet adaptedConfigurationSet = new AdaptedConfigurationSet();
		if (configurationSet instanceof LinuxProvisioningConfigurationSet) {
			LinuxProvisioningConfigurationSet linuxProvisioningConfigurationSet
								= (LinuxProvisioningConfigurationSet) configurationSet;
			adaptedConfigurationSet.configurationSetType = linuxProvisioningConfigurationSet
					.getConfigurationSetType();
			adaptedConfigurationSet.disableSshPasswordAuthentication = linuxProvisioningConfigurationSet
					.isDisableSshPasswordAuthentication();
			adaptedConfigurationSet.hostName = linuxProvisioningConfigurationSet
					.getHostName();
			adaptedConfigurationSet.userName = linuxProvisioningConfigurationSet
					.getUserName();
			adaptedConfigurationSet.userPassword = linuxProvisioningConfigurationSet
					.getUserPassword();
		} else {
			NetworkConfigurationSet networkConfigurationSet = (NetworkConfigurationSet) configurationSet;
			adaptedConfigurationSet.inputEndpoints = networkConfigurationSet
					.getInputEndpoints();
			adaptedConfigurationSet.configurationSetType = networkConfigurationSet
					.getConfigurationSetType();
		}

		return adaptedConfigurationSet;
	}
	
	/**
	 * 
	 * @author elip
	 *
	 */
	static class AdaptedConfigurationSet {

		@XmlElement(name = "ConfigurationSetType")
		private String configurationSetType = "LinuxProvisioningConfiguration";

		@XmlElement(name = "HostName")
		private String hostName;

		@XmlElement(name = "UserName")
		private String userName;

		@XmlElement(name = "UserPassword")
		private String userPassword;

		@XmlElement(name = "DisableSshPasswordAuthentication")
		private boolean disableSshPasswordAuthentication;

		@XmlElement(name = "InputEndpoints")
		private InputEndpoints inputEndpoints;

	}
}
