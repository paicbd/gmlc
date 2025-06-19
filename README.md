# GMLC – Gateway Mobile Location Center (Open Source Version)

##  Introduction to PAiCore Technologies GMLC

The **Gateway Mobile Location Center (GMLC)**, in conjunction with the **Serving Mobile Location Center (SMLC)**, empowers telecom operators to offer efficient and highly accurate **Location-Based Services (LBS)**—without relying on smartphone apps or an internet connection.

These capabilities are essential for a wide range of scenarios, including:

- Emergency location tracking  
- Context-aware targeted messaging  
- Network-based analytics  
- Regulatory compliance and public safety services  

The **GMLC** serves as the secure gateway between external applications and mobile network location data, while the **SMLC** performs location determination using radio measurements from nearby base stations.

###  Key Features of GMLC – Open Source Version

- Fully open source: fosters community collaboration and innovation  
- Operates independently of mobile apps or data connectivity  
- Transparent to the user—preserving battery life and privacy  
- Multi-protocol support: compatible with 2G (GSM), 3G (UMTS), 4G LTE, and 5G network positioning systems  
- Designed for telecom-grade performance and reliability  

---

##  Installation

### Prerequisites

- Java 11 (OpenJDK recommended)  
- Maven 3.x  
- Linux-based environment  
- (Optional) MySQL or other supported DB if needed  

### Getting Started

```bash
# 1. Clone the repository
git clone https://github.com/paicbd/gmlc.git
cd gmlc

# 2. Build the project
mvn clean install

# 3. Run the application
java -jar target/gmlc-*.jar
```

> Make sure the `JAVA_HOME` environment variable is properly set before building the project.



## Support and Contribution

This open source release is provided as part of PAiCore Technologies' commitment to open innovation.  
Community contributions are welcome via pull requests and issue discussions.

For commercial support, enterprise-grade deployments or consulting services, contact us at:  
🌐 https://paicore.tech

---

## License

This project is licensed under the **GNU General Public License v3.0 (GPL-3.0)**. See the [LICENSE](LICENSE) file for more details.
