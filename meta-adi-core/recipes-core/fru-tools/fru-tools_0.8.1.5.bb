SUMMARY = "Tools to display/manipulate FMC FRU info"
HOMEPAGE = "https://wiki.analog.com/resources/tools-software/linux-software/fru_dump?s[]=fru&s[]=tools"
SECTION = "console/utils"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://license.txt;md5=b234ee4d69f5fce4486a80fdaf4a4263"

BRANCH = "2018_R2"
# just pick the last revision on master
SRCREV = "${AUTOREV}"
SRC_URI = "git://github.com/analogdevicesinc/fru_tools.git;protocol=https;branch=${BRANCH}"
PV_append = "+git${SRCPV}"

S = "${WORKDIR}/git"

userlocaldir = "/usr/local"
bindest = "${userlocaldir}/bin"
masterfile = "${userlocaldir}/lib/fmc-tools/"

FILES_${PN} = "${bindest}/fru-dump ${masterfile} \
	${userlocaldir}/share/man/man1/fru-dump.1"

# so that the compiled artifact respects the OpenEmbedded ldflags
# fixes the "No GNU_HASH in the elf binary" package_qa error
TARGET_CC_ARCH += "${LDFLAGS}"

do_install() {
	oe_runmake PREFIX=${D}${userlocaldir} install
}
