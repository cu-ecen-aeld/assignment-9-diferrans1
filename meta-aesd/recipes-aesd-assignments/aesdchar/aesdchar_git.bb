# See https://git.yoctoproject.org/poky/tree/meta/files/common-licenses
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

inherit module

SRC_URI = "git://git@github.com/cu-ecen-aeld/assignments-3-and-later-diferrans1;protocol=ssh;branch=main"

PV = "1.0+git${SRCPV}"
SRCREV = "95bfb33b008bd2b4894d32d95860c9e07f2b7e11"

# Build from the aesd-char-driver subdirectory of the assignments repo
S = "${WORKDIR}/git/aesd-char-driver"

# Pass the staging kernel dir so the in-tree Makefile can find kernel headers
EXTRA_OEMAKE += 'KERNELDIR="${STAGING_KERNEL_DIR}"'

# Install load/unload scripts and sysvinit entry in addition to the .ko
# aesdchar_load and aesdchar_unload must be present in aesd-char-driver/
# (created as part of assignment 8 implementation)
do_install:append () {
	install -d ${D}${bindir}
	install -d ${D}${sysconfdir}/init.d
	install -d ${D}${sysconfdir}/rc0.d
	install -d ${D}${sysconfdir}/rc6.d
	install -d ${D}${sysconfdir}/rcS.d

	install -m 0755 ${S}/aesdchar_load  ${D}${bindir}/aesdchar_load
	install -m 0755 ${S}/aesdchar_unload ${D}${bindir}/aesdchar_unload

	# Use aesdchar_load as the sysvinit service script (loads module + creates device)
	install -m 0755 ${S}/aesdchar_load ${D}${sysconfdir}/init.d/aesdchar
	ln -rs ${D}${sysconfdir}/init.d/aesdchar ${D}${sysconfdir}/rcS.d/S90aesdchar.sh
	ln -rs ${D}${sysconfdir}/init.d/aesdchar ${D}${sysconfdir}/rc0.d/K90aesdchar.sh
	ln -rs ${D}${sysconfdir}/init.d/aesdchar ${D}${sysconfdir}/rc6.d/K90aesdchar.sh
}

FILES:${PN} += "${bindir}/aesdchar_load \
                ${bindir}/aesdchar_unload \
                ${sysconfdir}/init.d/aesdchar \
                ${sysconfdir}/rcS.d/S90aesdchar.sh \
                ${sysconfdir}/rc0.d/K90aesdchar.sh \
                ${sysconfdir}/rc6.d/K90aesdchar.sh"
