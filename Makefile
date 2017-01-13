export GATLING_BIN=/usr/local/share/gatling-2.2.3/bin/gatling.sh

clean:
	rm -rf /usr/local/share/gatling-2.2.3/target
	rm -f users-files.zip

test: clean
	$(GATLING_BIN) -sf $(PWD)/advanced/user-files/simulations \
		--no-reports \
		--data-folder $(PWD)/advanced/user-files/data \
		--bodies-folder $(PWD)/advanced/user-files/bodies \
		--results-folder /tmp/results \
		--simulation LoadTest

zip:
	cd advanced && \
	 zip -r user-files.zip user-files && \
	 mv user-files.zip $(PWD)/

report:
	$(GATLING_BIN) -ro $$(ls -ld /tmp/results/* | tail -n1 | cut -d ' ' -f12)